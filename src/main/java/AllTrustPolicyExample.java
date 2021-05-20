import com.alibaba.fastjson.JSONObject;
import com.futurebaobei.openapi.client.domain.BaobeiResponse;
import com.futurebaobei.openapi.client.domain.policy.*;
import com.futurebaobei.openapi.client.utils.BaobeiClient;
import generate.ChineseIDCardNumberGenerator;
import generate.ChineseMobileNumberGenerator;
import generate.ChineseNameGenerator;
import org.apache.commons.lang3.RandomStringUtils;


import java.util.*;

public class AllTrustPolicyExample {

    static BaobeiClient baobeiClient = new BaobeiClient("r2T5e2txNOLgwy4f",
            "YR10XaExwB7EjYRWwcn0KZA1Z4VYJ3Gb",
            "https://test-h5.futurebaobei.com");

    public static void main(String[] args) throws Exception {
        Integer count = Integer.valueOf(args[0]);
        for (int i = 0; i < count; i++) {
            outUnpaidOrder();//出单（未支付）
        }
    }

    //1=本人      2=配偶      3=子女      4=父母      5=其他
    private static void outUnpaidOrder() throws Exception {
        OutOrderRequest outOrderRequest = new OutOrderRequest();
        String orderNo = UUID.randomUUID().toString();
        outOrderRequest.setOutOrderNo(orderNo);
        outOrderRequest.setProductCode("RYB_CHANNEL");
        outOrderRequest.setAgentCode("agentCode");
        outOrderRequest.setChannel("channel");

        outOrderRequest.setSecondChannel("secondChannel");
        outOrderRequest.setCallbackUrl("https://www.baidu.com");

        String name = ChineseNameGenerator.getInstance().generate();
        String idCard = ChineseIDCardNumberGenerator.generate(2015, 2020);
        String mobile = ChineseMobileNumberGenerator.getInstance().generate();

        Insurant insurant = new Insurant();
        insurant.setOutId(RandomStringUtils.randomAlphanumeric(10));
        insurant.setName(name);
        insurant.setIdCard(idCard);
        insurant.setMobile(mobile);
        insurant.setRelationShip(3);
        insurant.setValidPeriod(1);
        insurant.setExpireDate("2023-01-16");


        String name3 = ChineseNameGenerator.getInstance().generate();
        String idCard3 = ChineseIDCardNumberGenerator.generate(2015, 2020);
        String mobile3 = ChineseMobileNumberGenerator.getInstance().generate();

        Insurant insurant2 = new Insurant();
        insurant2.setOutId(RandomStringUtils.randomAlphanumeric(10));
        insurant2.setName(name3);
        insurant2.setIdCard(idCard3);
        insurant2.setMobile(mobile3);
        insurant2.setRelationShip(3);
        insurant2.setValidPeriod(1);
        insurant2.setExpireDate("2023-03-16");


        String name2 = ChineseNameGenerator.getInstance().generate();
        String idCard2 = ChineseIDCardNumberGenerator.generate(1980, 1985);
        String mobile2 = ChineseMobileNumberGenerator.getInstance().generate();

        PolicyHolder policyHolder = new PolicyHolder();
        policyHolder.setOutId(RandomStringUtils.randomAlphanumeric(10));
        policyHolder.setName(name2);
        policyHolder.setIdCard(idCard2);
        policyHolder.setMobile(mobile2);


        outOrderRequest.setInsurants(Arrays.asList(insurant, insurant2));
//        outOrderRequest.setInsurants(Arrays.asList(insurant));
        outOrderRequest.setPolicyHolder(policyHolder);
        outOrderRequest.setPaymentTime(new Date());
        outOrderRequest.setUrlPath("/openapi/channel/policy/createUnpaidOrder");
        System.out.println(JSONObject.toJSON(outOrderRequest));

        BaobeiResponse<OutOrderResponse> baobeiResponse = baobeiClient.execute(outOrderRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            //成功 业务数据
            OutOrderResponse outOrderResponse = baobeiResponse.getDataObject();
            System.out.println(outOrderResponse);
        }
    }

    public static void claim() throws Exception {
        ClaimRequest claimRequest = new ClaimRequest();
        claimRequest.setOutOrderNo("148afb42-fc96-4bae-b6f8-ca8e485ba8d8");
        claimRequest.setUrlPath("/openapi/channel/policy/claim");
        BaobeiResponse<ClaimResponse> baobeiResponse = baobeiClient.execute(claimRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            //业务数据
            ClaimResponse claimResponse = baobeiResponse.getDataObject();
            System.out.println(claimResponse);
        }
    }


}
