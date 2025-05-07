package io.sample.playground.service;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FolderStorageSizeInGCPBuckets {

    // connect to cloud storage, ensure you also have Storage.List permissions on the bucket(s)
    private Storage connectToBucket() {
        //GCP projectId
        String projectId = "project-id";
        return StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    }

    /**
     * Method to get individual folder/folders storage size as cloud storage does only gives bucket size via monitoring
     * See how to get whole bucket size at @see <a href="https://cloud.google.com/storage/docs/getting-bucket-size#bucket-size">Determine a bucket's size</a>
     * This method is for a single or multiple buckets to get a single or multiple folders sizes within the bucket(s),
     * (Can traverse multiple buckets if folder names at same level are similar or just a single bucket)
     * - the level structure is as follows:
     * - for each folder size in each bucket: the directory is bucketName/folderName/
     * - therefore when using `Storage.BlobListOption.prefix(prefix)` then provide "folderName/"
     * - multiple buckets have the same folder naming at same level:
     * Uses:
     *
     * @param buckets:           one or more buckets to traverse
     * @param bucketFolderNames: one or more folders to get sizes for
     * @return Map containing folder(s) with their sizes in bytes.
     **/
    public Map<String, Long> getGCSBucketFolderStorageSize(List<String> buckets, List<String> bucketFolderNames) {
        // connect to GCS
        Storage storage = connectToBucket();


        // initialise map object with O bytes size
        Map<String, Long> bucketFolderSizes = bucketFolderNames.stream().collect(Collectors.toMap(folderName -> folderName, folderName -> 0L, (a, b) -> b));

        // iterate through each bucket and get sizes for the above folders
        buckets.stream().<Consumer<? super String>>map(bucketName -> folderName -> {
            log.info("Handling folder: {}, under bucket: {}", folderName, bucketName);

            // directory prefix of folder under bucket to get size for
            String directoryPrefix = folderName + "/";

            // total size bytes under the folder
            long totalSize = getTotalFilesSize(directoryPrefix, storage, bucketName);

            if (totalSize > 0) {
                if (bucketFolderSizes.containsKey(folderName)) {
                    bucketFolderSizes.put(folderName, bucketFolderSizes.get(folderName) + totalSize);
                } else {
                    bucketFolderSizes.put(folderName, totalSize);
                }
            }
        }).forEach(bucketFolderNames::forEach);

        bucketFolderSizes.forEach((folderName, size) -> log.info("Total size of files in all buckets for folder: " + folderName + " is: " + (double) size / 1000000 + " MBs"));
        return bucketFolderSizes;
    }

    private long getTotalFilesSize(String directoryPrefix, Storage storage, String bucket) {
        AtomicLong totalFilesSize = new AtomicLong();
        Page<Blob> blobs =
                storage.list(
                        bucket,
                        Storage.BlobListOption.prefix(directoryPrefix));

        blobs.streamAll().parallel().forEach(blob -> totalFilesSize.addAndGet(blob.getSize()));

        return totalFilesSize.get();
    }
}
