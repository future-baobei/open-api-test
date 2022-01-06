import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.futurebaobei.openapi.client.domain.BaobeiException;
import com.futurebaobei.openapi.client.domain.BaobeiResponse;
import com.futurebaobei.openapi.client.domain.policy.*;
import com.futurebaobei.openapi.client.utils.BaobeiClient;
import com.futurebaobei.openapi.client.utils.HttpClient;
import com.futurebaobei.openapi.client.utils.SignUtils;
import generate.ChineseIDCardNumberGenerator;
import generate.ChineseMobileNumberGenerator;
import generate.ChineseNameGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class PolicyExample {

    //    baobei-test
    static BaobeiClient baobeiClient = new BaobeiClient(
            "b8If1CYGdaJFvePl",
            "YR10XaExwB7EjYRWwcn0KZA1Z4VYJ3Gb",
            "http://localhost:8080");

    public static void main(String[] args) throws Exception {
//        outOrder();//出单
//        queryOutOrder();//出单查询
//        queryOrder();
        qaQuery();
//        claim();//获取权益url
//        qa();//获取权益url-qa
//        claimByOutOrderNo(); // 只根据外部订单号获取权益url
//        claimByPolicyHolderOutId(); // 根据投保人id获取权益url
//        buyDrug();//获取权益url-买药
//        update();//信息更新（更新投保人和被保人）
//        updateInsurant();//信息更新（更新被保人）
//        agentClaim();//代理人理赔页面
//        uploadByInputStream();//上传文件
//        uploadClaimImageData();//影像资料收集
//        claimResult();
//        queryPrivileges();
//        getInsurantOssTemporaryUrl();//获取签名url
//        getPolicyUrl();
//        isRefund(); // 查询是否可以退保
//        refund(); // 退保
//        freezePrivilege(); // 冻结权益
//        cancelRefund(); // 取消退保
//        claimApply();//理赔申请调用示例
    }

    private static void cancelRefund() throws BaobeiException {
        CancelFreezeRequest cancelFreezeRequest = new CancelFreezeRequest();
        cancelFreezeRequest.setOutOrderNo("218600000010464688");
        cancelFreezeRequest.setUrlPath("/openapi/channel/policy/cancelFreeze");
        BaobeiResponse<CancelFreezeResponse> execute = baobeiClient.execute(cancelFreezeRequest);
        System.out.println(JSONObject.toJSON(execute));
        if (execute.isSuccess()) {
            System.out.println("取消退保成功");
        }
    }

    private static void freezePrivilege() throws BaobeiException {
        FreezePrivilegeRequest freezePrivilegeRequest = new FreezePrivilegeRequest();
        freezePrivilegeRequest.setOutOrderNo("ORDER20210610TXPLR");
        freezePrivilegeRequest.setUrlPath("/openapi/channel/policy/freezePrivilege");
        BaobeiResponse<FreezePrivilegeResponse> execute = baobeiClient.execute(freezePrivilegeRequest);
        if (execute.isSuccess()) {
            System.out.println("冻结权益成功");
        }
        System.out.println(JSONObject.toJSON(execute));
//        claimApply();//理赔申请调用示例
    }

    private static void outOrder() throws Exception {
        OutOrderRequest outOrderRequest = new OutOrderRequest();
        String orderNo = UUID.randomUUID().toString();
        outOrderRequest.setOutOrderNo(orderNo);
        outOrderRequest.setOutPolicyNo(UUID.randomUUID().toString().replace("-", ""));
        outOrderRequest.setProductCode("DOCTOR_NIU_FAMILY_PLAN_B");
        outOrderRequest.setAgentCode("agentCode");
        outOrderRequest.setChannel("channel");
        outOrderRequest.setBranchCompany("branchCompany");
//        outOrderRequest.setActiveTime("2022-10-10 00:00:00");

        Insurant insurant = new Insurant();
        insurant.setOutId(UUID.randomUUID().toString().replace("-", ""));
        insurant.setName(ChineseNameGenerator.getInstance().generate());
        insurant.setCertificateType(CertificateType.FAMILY_REGISTER);
        insurant.setCertificateNo(ChineseIDCardNumberGenerator.getInstance().generate());
        insurant.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
        insurant.setAddress("address");
        insurant.setEmail("email");
        insurant.setOccupation("occupation");
        insurant.setPostcode("postcode");
        insurant.setRelationShip(3);
        insurant.setCountry("country");
        insurant.setProvince("120000");
        insurant.setCity("120000");
        insurant.setArea("120112");

        PolicyHolder policyHolder = new PolicyHolder();
        policyHolder.setOutId(UUID.randomUUID().toString().replace("-", ""));
        policyHolder.setName(ChineseNameGenerator.getInstance().generate());
        policyHolder.setCertificateType(CertificateType.ID);
        policyHolder.setCertificateNo(ChineseIDCardNumberGenerator.getInstance().generate());
        policyHolder.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
        policyHolder.setValidPeriod(1);//0=长期 1=五年 2=十年 3=二十年
        policyHolder.setExpireDate("2025-01-01");
        policyHolder.setEmail("email");
        policyHolder.setOccupation("occupation");
        policyHolder.setPostcode("postcode");
        policyHolder.setProvince("110000");
        policyHolder.setCity("110000");
        policyHolder.setArea("110101");
        policyHolder.setAddress("address");
        policyHolder.setCountry("country");

        outOrderRequest.setInsurants(Arrays.asList(insurant));
        outOrderRequest.setPolicyHolder(policyHolder);
        outOrderRequest.setPaymentTime(new Date());
        outOrderRequest.setUrlPath("/openapi/channel/policy/create");
        System.out.println(JSONObject.toJSON(outOrderRequest));
        BaobeiResponse<OutOrderResponse> baobeiResponse = baobeiClient.execute(outOrderRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            //成功 业务数据
            OutOrderResponse outOrderResponse = baobeiResponse.getDataObject();
            System.out.println(outOrderResponse);
        }
        if (baobeiResponse.unkownError()) {
            //未知异常，有极小的概率出现，订单状态未知，调用查询订单确认订单状态
        }
    }

    public static void claim() throws Exception {
        ClaimRequest claimRequest = new ClaimRequest();
        claimRequest.setOutOrderNo("218600000010464688");
//        claimRequest.setRequestType("APP");
//        PolicyHolder policyHolder = new PolicyHolder();
//        policyHolder.setOutId("200258914621");
//        policyHolder.setName(ChineseNameGenerator.getInstance().generate());
//        policyHolder.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());
//        policyHolder.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
//        claimRequest.setPolicyHolder(policyHolder);

        Insurant insurant = new Insurant();
        insurant.setOutId("0000269222");
//        insurant.setName(ChineseNameGenerator.getInstance().generate());
//        insurant.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());
//        insurant.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
//        insurant.setRelationShip(3);
        claimRequest.setInsurant(insurant);
        claimRequest.setUrlPath("/openapi/channel/policy/claim");
        BaobeiResponse<ClaimResponse> baobeiResponse = baobeiClient.execute(claimRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            //业务数据
            ClaimResponse claimResponse = baobeiResponse.getDataObject();
            System.out.println(claimResponse);
        }
    }

    public static void claimByOutOrderNo() throws Exception {
        ClaimRequest claimRequest = new ClaimRequest();
        claimRequest.setOutOrderNo("218600000010464688");
        claimRequest.setPlatform("APP");
        claimRequest.setUrlPath("/openapi/channel/policy/claimByOutOrderNo");
        BaobeiResponse<ClaimResponse> baobeiResponse = baobeiClient.execute(claimRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            //业务数据
            ClaimResponse claimResponse = baobeiResponse.getDataObject();
            System.out.println(claimResponse);
        }
    }

    public static void claimByPolicyHolderOutId() throws Exception {
        ClaimRequest claimRequest = new ClaimRequest();
        claimRequest.setPolicyHolderOutId("0000269529");
        claimRequest.setPlatform("APP");
        claimRequest.setUrlPath("/openapi/channel/policy/claimByPolicyHolderOutId");
        BaobeiResponse<ClaimResponse> baobeiResponse = baobeiClient.execute(claimRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            //业务数据
            ClaimResponse claimResponse = baobeiResponse.getDataObject();
            System.out.println(claimResponse);
        }
    }

//    {"branchCompany":"branchCompany","productCode":"RYB","agentCode":"agentCode","channel":"channel","paymentTime":1612342448524,"insurants":[{"address":"address","occupation":"occupation","idCard":"360793199705030889","mobile":"13473503262","name":"卢母","postcode":"postcode","expireDate":"2025-01-01","outId":"dafb49f43c6f450598309f7fe6a08988","relationShip":3,"validPeriod":1,"email":"email"}],"outOrderNo":"5c51b705-2ae5-46ba-97f2-9c00c8adc227","policyHolder":{"address":"address","occupation":"occupation","idCard":"135848199409105415","mobile":"14550576667","name":"宇文弃","postcode":"postcode","expireDate":"2025-01-01","outId":"8eef4acc6ecb4860a5ce032fb0af65bb","validPeriod":1,"email":"email"}}

    public static void qa() throws Exception {
        QARequest qaRequest = new QARequest();
        qaRequest.setOutOrderNo("218600000010464688");
        qaRequest.setType("UPGRADE");
        qaRequest.setProblemId("121423542");
//        qaRequest.setPlatform("APP");

//        PolicyHolder policyHolder = new PolicyHolder();
//        policyHolder.setOutId("f8897dc9-1a7d-4c67-8c1a-b42aaf3ae6a9");
//        policyHolder.setName(ChineseNameGenerator.getInstance().generate());
//        policyHolder.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());
//        policyHolder.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
//        policyHolder.setName("一二三");
//        policyHolder.setIdCard("110101199003070572");
//        policyHolder.setMobile("15011111111");

        Insurant insurant = new Insurant();
        insurant.setOutId("0000269222");
//        insurant.setName(ChineseNameGenerator.getInstance().generate());
//        insurant.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());
//        insurant.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
//        insurant.setRelationShip(3);

//        claimRequest.setPolicyHolder(policyHolder);
        qaRequest.setInsurant(insurant);
//        QAProblem qaProblem = new QAProblem();
//        qaProblem.setAsk("test");
//        qaRequest.setProblem(qaProblem);

        BaobeiResponse<ClaimResponse> baobeiResponse = baobeiClient.execute(qaRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            //业务数据
            ClaimResponse claimResponse = baobeiResponse.getDataObject();
            System.out.println(claimResponse);
        }
    }

    public static void buyDrug() throws Exception {
        BuyDrugRequest buyDrugRequest = new BuyDrugRequest();
        buyDrugRequest.setOutOrder("218600000010464688");
        buyDrugRequest.setPrescriptionNo("CY2021022214454906698");
        buyDrugRequest.setProblemId("121423542");
        buyDrugRequest.setPlatform("APP");
        BaobeiResponse<ClaimResponse> baobeiResponse = baobeiClient.execute(buyDrugRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            ClaimResponse buyDrugResponse = baobeiResponse.getDataObject();
            System.out.println(buyDrugResponse);
        }

    }

    public static void queryOutOrder() throws Exception {
        QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
        queryOrderRequest.setOutOrderNo("218600000010464688");
        queryOrderRequest.setUrlPath("/openapi/channel/policy/query");
        BaobeiResponse<QueryOrderResponse> baobeiResponse = baobeiClient.execute(queryOrderRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            QueryOrderResponse queryOrderResponse = baobeiResponse.getDataObject();
            System.out.println(queryOrderResponse);
        }
    }


    public static void update() throws Exception {
        UpdateOrderRequest request = new UpdateOrderRequest();
        Insurant insurant = new Insurant();
        insurant.setOutId("bf1ec4a07c8743a7bca516345e4556f4");
        insurant.setName(ChineseNameGenerator.getInstance().generate());
        insurant.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());
        insurant.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
        insurant.setValidPeriod(1);//0=长期 1=五年 2=十年 3=二十年
        insurant.setExpireDate("2026-01-01");
        insurant.setAddress("address1");
        insurant.setEmail("email1");
        insurant.setOccupation("occupation1");
        insurant.setPostcode("postcode1");
        insurant.setRelationShip(3);
        insurant.setProvince("110000");
        insurant.setCity("110000");
        insurant.setArea("110101");

        PolicyHolder policyHolder = new PolicyHolder();
        policyHolder.setOutId("577506b0a7db4ad79b4d5e9bb29c79bd");
        policyHolder.setName(ChineseNameGenerator.getInstance().generate());
        policyHolder.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());
        policyHolder.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
        policyHolder.setValidPeriod(1);//0=长期 1=五年 2=十年 3=二十年
        policyHolder.setExpireDate("2026-01-01");
        policyHolder.setAddress("address1");
        policyHolder.setEmail("email1");
        policyHolder.setOccupation("occupation1");
        policyHolder.setPostcode("postcode1");
        policyHolder.setProvince("120000");
        policyHolder.setCity("120000");
        policyHolder.setArea("120112");

        request.setInsurants(Arrays.asList(insurant));
        request.setOutOrderNo("df638186-b84a-4488-b0af-b823682811f9");
        request.setDescription("");
        request.setPolicyHolder(policyHolder);

        request.setUrlPath("/openapi/channel/policy/update");
        BaobeiResponse<UpdateOrderResponse> baobeiResponse = baobeiClient.execute(request);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            UpdateOrderResponse updateOrderResponse = baobeiResponse.getDataObject();
            System.out.println(JSONObject.toJSON(updateOrderResponse));
        }
    }

    public static void updateInsurant() throws Exception {
        UpdateInsurantRequest request = new UpdateInsurantRequest();
        Insurant insurant = new Insurant();
        insurant.setOutId("QJMUdZC9DI");
        insurant.setName("小张");
        insurant.setIdCard("110101200303071855");
        insurant.setMobile("11700000000");
        insurant.setRelationShip(3);

        request.setOutOrderNo("145df518-ed8c-4585-a9fa-5a4f362b372d");
        request.setDescription("");
        request.setInsurant(insurant);
        request.setUrlPath("/openapi/channel/policy/update/insurant");
        BaobeiResponse<UpdateInsurantResponse> baobeiResponse = baobeiClient.execute(request);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            UpdateInsurantResponse updateInsurantResponse = baobeiResponse.getDataObject();
            System.out.println(JSONObject.toJSON(updateInsurantResponse));
        }
    }

    private static void agentClaim() throws Exception {
        AgentClaimRequest request = new AgentClaimRequest();
        request.setAgentCode("agentCode");
        request.setAgentName(ChineseNameGenerator.getInstance().generate());
        request.setAgentMobile(ChineseMobileNumberGenerator.getInstance().generate());
        request.setUrlPath("/openapi/channel/policy/agent/claim");
        BaobeiResponse<AgentClaimResponse> baobeiResponse = baobeiClient.execute(request);
        System.out.println(JSONObject.toJSON(baobeiResponse));
    }

    public static void uploadClaimImageData() throws Exception {
        UploadImageDataRequest uploadImageDataRequest = new UploadImageDataRequest();
        uploadImageDataRequest.setOutOrderNo("c5f55906-07e7-4bb4-825d-16d42b07a38b");
        ImageVO imageVO = new ImageVO();
        imageVO.setImageKey("sunshine/2021012060078dbc92093e1d140429d8.jpg");
        imageVO.setMaterialType("IDENTITY_FACE");
        imageVO.setOutId("CPT09000200104693335");
        imageVO.setType("INSURANT");
        uploadImageDataRequest.setImages(Arrays.asList(imageVO));
        uploadImageDataRequest.setUrlPath("/openapi/channel/policy/uploadClaimImage");
        BaobeiResponse execute = baobeiClient.execute(uploadImageDataRequest);
        if (execute.isSuccess()) {
            System.out.println("上传成功");
        } else {
            System.out.println("上传失败");
            System.out.println(execute);
        }
    }

    public static void uploadByInputStream() throws Exception {
        BaobeiResponse<UploadFileResponse> execute = baobeiClient.execute(new UploadByInputStreamRequest(new FileInputStream(new File("/Users/apple/Downloads/ststest.jpg")), "jpg", "218600000010473196"));
        System.out.println("key =================== " + execute.getDataObject().getKey());
    }

    public static void claimResult() throws Exception {
        ClaimResultRequest claimResultRequest = new ClaimResultRequest();
        claimResultRequest.setClaimRegisterNo("claimRegisterNo");
        claimResultRequest.setPolicyNo("policyNo");
        claimResultRequest.setStatus("SUCCESS");
        claimResultRequest.setRejectReason("rejectReason");
        claimResultRequest.setRejectDescription("rejectDescription");
        claimResultRequest.setAgainApplicantDate("againApplicantDate");
        BaobeiResponse<ClaimResultResponse> baobeiResponse = baobeiClient.execute(claimResultRequest);
        System.out.println("key =================== " + baobeiResponse);
    }

    public static void queryPrivileges() throws Exception {
        QueryPrivilegesRequest queryPrivilegesRequest = new QueryPrivilegesRequest();
        Insurant insurant = new Insurant();
        insurant.setOutId("0000269222");

        queryPrivilegesRequest.setInsurant(insurant);
        queryPrivilegesRequest.setOutOrderNo("218600000010464688");
        System.out.println(JSON.toJSONString(queryPrivilegesRequest));
        BaobeiResponse<QueryPrivilegesResponse> baobeiResponse = baobeiClient.execute(queryPrivilegesRequest);
        System.out.println(baobeiResponse);


    }

    public static void getInsurantOssTemporaryUrl() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("https://insurance-oss.oss-cn-zhangjiakou.aliyuncs.com/guofu/claimauth/20210521/claim-applicant-guofu_2021052117313607640918.pdf");
        BaobeiResponse<TemporaryUrlResponse> execute = baobeiClient.execute(TemporaryUrlRequest.builder().urls(list).build());
        List<String> urls = execute.getDataObject().getUrls();
        System.out.println("temp ======== " + urls);
        //url转inputstream
        List<InputStream> inputStreams = baobeiClient.downloadFiles(urls);
    }

    private static void getPolicyUrl() throws BaobeiException {
        BaobeiResponse<AcquirePolicyResponse> execute = baobeiClient.execute(AcquirePolicyUrlRequest.builder().outOrderNo("ibaqoslzyk").build());
        System.out.println("policyUrl ============== " + execute.getDataObject().getPolicyUrl());
    }

    private static void isRefund() throws BaobeiException {
        BaobeiResponse execute = baobeiClient.execute(RefundOrderQueryRequest.builder()
                .outOrderNo("ORDER20210618RLQJO").build());
        System.out.println(JSONObject.toJSONString(execute));
        System.out.println("是否可以退保，code为 ============== " + execute.getCode());
    }

    public static void refund() throws Exception {
        RefundOrderRequest refundOrderRequest = new RefundOrderRequest();
        refundOrderRequest.setOutOrderNo("ORDER20210618ZEFUC");
        refundOrderRequest.setRefundTime(new Date());
        refundOrderRequest.setUrlPath("/openapi/channel/policy/refund");
        BaobeiResponse<RefundOrderResponse> baobeiResponse = baobeiClient.execute(refundOrderRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
//            退保没有业务参数，此时可以认为退保成功
        }
    }

    public static void queryOrder() throws Exception {
        OrderQueryRequest request = new OrderQueryRequest();
        request.setOrderNo("2020071415150104100905");
        BaobeiResponse<OrderQueryResponse> baobeiResponse = baobeiClient.execute(request);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            OrderQueryResponse orderQueryResponse = baobeiResponse.getDataObject();
            System.out.println(orderQueryResponse);
        }
    }

    public static void qaQuery() throws Exception {
        QAQueryRequest request = new QAQueryRequest();
        request.setPartnerUserId("e49ea52b-3f79-44b6-9046-e708c0d02011");
        BaobeiResponse<QAQueryResponse> baobeiResponse = baobeiClient.execute(request);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            QAQueryResponse qaQueryResponse = baobeiResponse.getDataObject();
            System.out.println(qaQueryResponse);
        }
    }

    public static void claimApply() throws Exception {
        String appId = "";
        String appSecret = "";
        String data = "";
        String url = "";

        HashMap<String, String> params = new HashMap();
        params.put("appId", appId);
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("data", data);
        params.put("sign", SignUtils.sign(params, appSecret));
        String response = new HttpClient().postRequestAsString(url, params, 10000, 10000);
        System.out.println(response);
    }

}
