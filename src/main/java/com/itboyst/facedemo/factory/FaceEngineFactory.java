package com.itboyst.facedemo.factory;

import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class FaceEngineFactory extends BasePooledObjectFactory<FaceEngine> {

    private String appId;
    private String sdkKey;
    private String sdkLibPath;
    private EngineConfiguration engineConfiguration;
    private Integer detectFaceMaxNum = 10;
    private Integer detectFaceScaleVal = 16;
    private DetectMode detectMode = DetectMode.ASF_DETECT_MODE_IMAGE;
    private DetectOrient detectFaceOrientPriority = DetectOrient.ASF_OP_0_ONLY;


    public FaceEngineFactory(String sdkLibPath, String appId, String sdkKey, EngineConfiguration engineConfiguration) {
        this.sdkLibPath = sdkLibPath;
        this.appId = appId;
        this.sdkKey = sdkKey;
        this.engineConfiguration = engineConfiguration;

    }


    @Override
    public FaceEngine create() throws Exception {

        FaceEngine faceEngine = new FaceEngine(sdkLibPath);
        //-=======================
        int activeCode = faceEngine.activeOnline(appId, sdkKey);
        System.out.println("faceEngineActiveCode:" + activeCode + "==========================");
        int initCode = faceEngine.init(engineConfiguration);
        System.out.println("faceEngineInitCode:" + initCode + "==========================");
        //设置活体检测的值，文档要求是0.5、0.7，但是准确度不高（暂时没找到问题所在）所以定位0.8f
        int errorCode = faceEngine.setLivenessParam(0.8f, 0.7f);
        return faceEngine;
    }

    @Override
    public PooledObject<FaceEngine> wrap(FaceEngine faceEngine) {
        return new DefaultPooledObject<>(faceEngine);
    }


    @Override
    public void destroyObject(PooledObject<FaceEngine> p) throws Exception {
        FaceEngine faceEngine = p.getObject();
        int unInitCode = faceEngine.unInit();
        System.out.println("faceEngineUnInitCode:" + unInitCode + "==========================");
        super.destroyObject(p);
    }
}
