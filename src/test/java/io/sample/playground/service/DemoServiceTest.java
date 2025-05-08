package io.sample.playground.service;

import io.sample.playground.client.TempClient;
import io.sample.playground.client.TempClientConfig;
import io.sample.playground.dto.HubspotDTO;
import io.sample.playground.dto.MQMessage;
import io.sample.playground.dto.MongoMessageDTO;
import io.sample.playground.dto.PostgresMessageDTO;
import io.sample.playground.mq.DemoQueue;
import io.sample.playground.repository.MongoMessageRepository;
import io.sample.playground.repository.PostgresMessageRepository;
import io.sample.playground.patterns.spring_event_driven.Interviewee;
import io.sample.playground.patterns.spring_event_driven.JobApplication;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class DemoServiceTest {
    @Mock
    private DemoQueue queue;
    @Mock
    private MongoMessageRepository mongoMessageRepository;
    @Mock
    private PostgresMessageRepository postgresMessageRepository;
    @Mock
    private TempClientConfig tempClientConfig;
    @Mock
    private TempClient tempClient;
    @Mock
    private JobApplication jobApplication;
    @InjectMocks
    private DemoService demoService;
    private Call<TempClient.HubResponse> call;

    @BeforeEach
    void setUp() {
        call = mock(Call.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void when_sending_a_message_then_verify_it_is_persisted_and_published() {
        MQMessage message = new MQMessage();
        message.setName("name");
        message.setDetails("details");

        demoService.sendMessage(message);


        verify(postgresMessageRepository, times(1)).save(any(PostgresMessageDTO.class));
        verify(mongoMessageRepository, times(1)).save(any(MongoMessageDTO.class));
        verify(queue, times(1)).publish(any(MQMessage.class));
    }
    @Test
    void when_testing_API_get_execution_success_then_return_execution() throws IOException {
        String executionName = "test-execution";
        String executionId = "executionId";

        ResponseBody body = ResponseBody.create(executionName, MediaType.get("application/json"));
        Response response = Response.success(body);

        when(tempClientConfig.getAPIClient()).thenReturn(tempClient);
        when(tempClient.getResponseObject(executionId)).thenReturn(call);
        when(call.execute()).thenReturn(response);

        List<HubspotDTO> result = demoService.getResponseObject(executionId);

        System.out.println(result);
        assertEquals(executionName, result);
    }

    @Test
    void when_testing_API_get_execution_failure_then_return_null() throws IOException {
        String executionName = "test-execution";
        String executionId = "executionId";

        ResponseBody body = ResponseBody.create("Execution not found", MediaType.get("application/json"));
        Response response = Response.error(404,body);

        when(tempClientConfig.getAPIClient()).thenReturn(tempClient);
        when(tempClient.getResponseObject(executionId)).thenReturn(call);
        when(call.execute()).thenReturn(response);

        List<HubspotDTO> result = demoService.getResponseObject(executionId);

        assertNull(result);
    }

    @Test
    void testSubmitApplication() {
        Interviewee test = new Interviewee();
        test.setNameOfApplicant("John Doe");

        demoService.submitApplication(test);

        verify(jobApplication, times(1)).applyForJob(test);
    }

    @Test
    public void calculateFinalSpeed() {
        int price  = 100;
        double discountedPrice = 0;
        int weightBasket = 12;
        discount discount = DemoServiceTest.discount.weight;
        switch (discount) {
            case weight:
                if (weightBasket > 10) {
                    discountedPrice = price - (price * 0.18);
                } else {
                    discountedPrice = price - (price - 0.6);
                }
                break;
            case standard:
                discountedPrice = price - (price * 0.12);
                break;
            case seaasonal:
                discountedPrice = price - (price * 0.06);
                break;
        }
        System.out.println(discountedPrice);

    }
    enum discount {
        weight,
        standard,
        seaasonal
    }

    @Test
    public void testFinobachiSeries() {
        int testCount = 25;
        //System.out.println(fibonacci(testCount));

        //System.out.println(tribonachi(testCount));
        int[] nums = {2,7,9,4,1};
        //System.out.println(rob(nums));

        String str = "babad";
        //System.out.println(longestPalindrome(str));

        String s = "applepenapple";
        //List<String> wordDict = Arrays.asList("pile", "apple","pen");
        //System.out.println(canBeSegmented(s, wordDict) ? "true" : "false");
        generateWellOrdered(3);
    }

    public boolean containsDictWords(String str, List<String> strArray) {
        int lastIndex = 0;

        /*for (String word : strArray) {
            System.out.println("word: " + word);
            if (!str.contains(word)) {
                return false;
            } else {
                lastIndex = str.indexOf(word) + word.length();
                System.out.println("lastIndex: " + lastIndex);
                str = str.substring(lastIndex);
                System.out.println("str: " + str);
            }
        }
        return true;*/

        // check first word in array if contained in string
        // if it is, remove it from the string
        // if string is empty, return true
        // if not, check next word in array is contained in string
        // if it is, remove it from the string
        // if string is empty, return true
        // if not, check next word in array is contained in string
        // do this until string is empty or the word left in string is not in the array
        boolean found = false;
        //while (!found) {
            for (String word : strArray) {
                System.out.println("checking: " + str + " for word: " + word);
                if (str.startsWith(word)) {
                    str = str.substring(str.indexOf(word) + word.length());
                    System.out.println("str: " + str + " after removing word: " + word);
                    found = true;
                }
            }

        //}
        return found;
    }

    // given list of numbers
    // and a specified length k
    // return k numbers in the list that are ascending left to right
    private List<Integer> getAscendingNumbers(List<Integer> numbers, int length) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i) < numbers.get(j)) {
                    result.add(numbers.get(i));
                    result.add(numbers.get(j));
                }
            }
        }
        return result;
    }
    public boolean areNumbersAscending(String s) {
        int prev = 0;

        for (final String token : s.split(" "))
            if (Character.isDigit(token.charAt(0))) {
                final int num = Integer.parseInt(token);
                if (num <= prev)
                    return false;
                prev = num;
            }

        return true;
    }

    private void printWellOrdered(int number,
                                 int x, int k)
    {
        if (k == 0)
        {
            System.out.print(number+" ");
            return;
        }

        // Try all possible greater digits
        for (int i = (x + 1); i < 10; i++)
            printWellOrdered(number * 10 +
                    i, i, k - 1);
    }

    // Generates all well ordered
    // numbers of length k.
    private void generateWellOrdered(int k) {
        printWellOrdered(0, 0, k);
    }


    private boolean canBeSegmented(String str, List<String> strArray) {
        boolean found = false;

        String remaining = null;
        for (String word : strArray) {
            String continedWord = isContainWord(str, strArray);
            if (StringUtils.isNotBlank(continedWord)) {
                remaining = str.substring(continedWord.length());
                System.out.println("str: " + remaining + " after removing word: " + continedWord);
                found = true;
                break;
            } else {
                found = false;
                break;
            }
        }

        if (found & StringUtils.isNotBlank(remaining)) {
            return canBeSegmented(remaining, strArray);
        }

        return found;
    }
    private String isContainWord(String str, List<String> strArray) {
        for (String word : strArray) {
            if (str.startsWith(word)) {
                System.out.println("true: " + str + " starts word: " + word);
                return word;
            }
        }
        System.out.println("false: " + str + " not starts word: " + strArray.toString());
        return "";
    }
    private int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private int tribonachi(int n) {
        if (n <= 1) {
            return n;
        }
        if (n == 2) {
            return tribonachi(n - 1);// + tribonachi(0);
        }
        return tribonachi(n - 1) + tribonachi(n - 2) + tribonachi(n - 3);
    }

    public int rob(int[] nums) {
        int sum = 0;
        for(int i=0; i<nums.length; i++) {
            if (i % 2 == 0 || i <= 1) {
                continue;
            }
            // add two adjacent numbers
            System.out.println("i: " + i + " nums[i-2]: " + nums[i - 1]);
            int currentSum = nums[i] + nums[i - 1];

            if (currentSum > sum) {
                sum = currentSum;
            }
        }
        return sum;
    }

    public String findLongestPliandrome(String str) {
        String reversed = StringUtils.reverse(str);
        String secondary = null;
        String singly = null;
        int length = str.length();

        if (str.equals(reversed)) {
            return str;
        }

        for (int i = 0; i < str.length(); i++) {
            for(int j = i+1; j< str.length(); j++) {
                if (reversed.contains(str.substring(i,j))) {
                    secondary = str.substring(i,j);
                }
                //if ()
            }

        }
        return null;
    }

    public String longestPalindrome(String s) {
        for (int length = s.length(); length > 0; length--) {
            for (int start = 0; start <= s.length() - length; start++) {
                if (check(start, start + length, s)) {
                    return s.substring(start, start + length);
                }
            }
        }

        return "";
    }

    private boolean check(int i, int j, String s) {
        int left = i;
        int right = j - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}