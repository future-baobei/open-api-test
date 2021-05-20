

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.futurebaobei.openapi.client.domain.BaobeiResponse;
import com.futurebaobei.openapi.client.domain.policy.*;
import com.futurebaobei.openapi.client.utils.BaobeiClient;
import generate.ChineseIDCardNumberGenerator;
import generate.ChineseMobileNumberGenerator;
import generate.ChineseNameGenerator;


import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class PolicyExample {

    //    baobei-test
    static BaobeiClient baobeiClient = new BaobeiClient(
            "",
            "",
            "");

    public static void main(String[] args) throws Exception {
//        outOrder();//出单
//        queryOutOrder();//出单查询
//        claim();//获取权益url
//        qa();//获取权益url-qa
//        buyDrug();//获取权益url-买药
//        refund();//退保
//        update();//信息更新（更新投保人和被保人）
//        updateInsurant();//信息更新（更新被保人）
//        agentClaim();//代理人理赔页面
//        uploadByInputStream();//上传文件
//        uploadClaimImageData();//影像资料收集
//        claimResult();
//        queryPrivileges();
    }

    private static void outOrder() throws Exception {
        OutOrderRequest outOrderRequest = new OutOrderRequest();
        String orderNo = UUID.randomUUID().toString();
        outOrderRequest.setOutOrderNo(orderNo);
        outOrderRequest.setOutPolicyNo(UUID.randomUUID().toString().replace("-", ""));
        outOrderRequest.setProductCode("ALL_TRUST_BHYB");
        outOrderRequest.setAgentCode("agentCode");
        outOrderRequest.setChannel("channel");
        outOrderRequest.setBranchCompany("branchCompany");

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
        policyHolder.setProvince("province");
        policyHolder.setCity("city");
        policyHolder.setArea("area");
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
        claimRequest.setOutOrderNo("683e83a6-554b-4e1c-839a-eff17d70be30");
//        PolicyHolder policyHolder = new PolicyHolder();
//        policyHolder.setOutId("200258914621");
//        policyHolder.setName(ChineseNameGenerator.getInstance().generate());
//        policyHolder.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());
//        policyHolder.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
//        claimRequest.setPolicyHolder(policyHolder);

        Insurant insurant = new Insurant();
        insurant.setOutId("f16e50705c9b42789a1bd15183c2a5a0");
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

//    {"branchCompany":"branchCompany","productCode":"RYB","agentCode":"agentCode","channel":"channel","paymentTime":1612342448524,"insurants":[{"address":"address","occupation":"occupation","idCard":"360793199705030889","mobile":"13473503262","name":"卢母","postcode":"postcode","expireDate":"2025-01-01","outId":"dafb49f43c6f450598309f7fe6a08988","relationShip":3,"validPeriod":1,"email":"email"}],"outOrderNo":"5c51b705-2ae5-46ba-97f2-9c00c8adc227","policyHolder":{"address":"address","occupation":"occupation","idCard":"135848199409105415","mobile":"14550576667","name":"宇文弃","postcode":"postcode","expireDate":"2025-01-01","outId":"8eef4acc6ecb4860a5ce032fb0af65bb","validPeriod":1,"email":"email"}}

    public static void qa() throws Exception {
        QARequest qaRequest = new QARequest();
        qaRequest.setOutOrderNo("ecpic-user001");
        qaRequest.setType("UPGRADE");
        qaRequest.setProblemId("121423542");

//        PolicyHolder policyHolder = new PolicyHolder();
//        policyHolder.setOutId("f8897dc9-1a7d-4c67-8c1a-b42aaf3ae6a9");
//        policyHolder.setName(ChineseNameGenerator.getInstance().generate());
//        policyHolder.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());
//        policyHolder.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
//        policyHolder.setName("一二三");
//        policyHolder.setIdCard("110101199003070572");
//        policyHolder.setMobile("15011111111");

        Insurant insurant = new Insurant();
        insurant.setOutId("ecpic-user001");
//        insurant.setName(ChineseNameGenerator.getInstance().generate());
//        insurant.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());
//        insurant.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
//        insurant.setRelationShip(3);

//        claimRequest.setPolicyHolder(policyHolder);
        qaRequest.setInsurant(insurant);

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
        buyDrugRequest.setOutOrder("ecpic-user002");
        buyDrugRequest.setPrescriptionNo("CY2021022214454906698");
        buyDrugRequest.setProblemId("13399292");
        BaobeiResponse<BuyDrugResponse> baobeiResponse = baobeiClient.execute(buyDrugRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            BuyDrugResponse buyDrugResponse = baobeiResponse.getDataObject();
            System.out.println(buyDrugResponse);
        }

    }

    public static void queryOutOrder() throws Exception {
        QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
        queryOrderRequest.setOutOrderNo("59cc725f-278d-4ba2-bfaf-c60398ff8cd8");
        queryOrderRequest.setUrlPath("/openapi/channel/policy/query");
        BaobeiResponse<QueryOrderResponse> baobeiResponse = baobeiClient.execute(queryOrderRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
            QueryOrderResponse queryOrderResponse = baobeiResponse.getDataObject();
            System.out.println(queryOrderResponse);
        }
    }

    public static void refund() throws Exception {
        RefundOrderRequest refundOrderRequest = new RefundOrderRequest();
        refundOrderRequest.setOutOrderNo("cbf3727a-7ea1-4026-a16a-ef9ffdf85d41");
        refundOrderRequest.setRefundTime(new Date());
        refundOrderRequest.setUrlPath("/openapi/channel/policy/refund");
        BaobeiResponse<RefundOrderResponse> baobeiResponse = baobeiClient.execute(refundOrderRequest);
        System.out.println(JSONObject.toJSON(baobeiResponse));
        if (baobeiResponse.isSuccess()) {
//            退保没有业务参数，此时可以认为退保成功
        }
    }

    public static void update() throws Exception {
        UpdateOrderRequest request = new UpdateOrderRequest();
        Insurant insurant = new Insurant();
        insurant.setOutId("386ad17c512e41988390b6f2dd2fb162");
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

        PolicyHolder policyHolder = new PolicyHolder();
        policyHolder.setOutId("46cdbe0338fe4360b06ebcaa8e95865d");
        policyHolder.setName(ChineseNameGenerator.getInstance().generate());
        policyHolder.setIdCard(ChineseIDCardNumberGenerator.getInstance().generate());
        policyHolder.setMobile(ChineseMobileNumberGenerator.getInstance().generate());
        policyHolder.setValidPeriod(1);//0=长期 1=五年 2=十年 3=二十年
        policyHolder.setExpireDate("2026-01-01");
        policyHolder.setAddress("address1");
        policyHolder.setEmail("email1");
        policyHolder.setOccupation("occupation1");
        policyHolder.setPostcode("postcode1");

        request.setInsurants(Arrays.asList(insurant));
        request.setOutOrderNo("9ace2e19-1607-45e6-9e80-b555b0d7dcd6");
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
        insurant.setOutId("afb2bce11c494d74b80a6229b8385082");

        queryPrivilegesRequest.setInsurant(insurant);
        queryPrivilegesRequest.setOutOrderNo("5913de06-4c87-4e54-a32e-3c43efa087ec");
        System.out.println(JSON.toJSONString(queryPrivilegesRequest));
        BaobeiResponse<QueryPrivilegesResponse> baobeiResponse = baobeiClient.execute(queryPrivilegesRequest);
        System.out.println(baobeiResponse);


    }

}
