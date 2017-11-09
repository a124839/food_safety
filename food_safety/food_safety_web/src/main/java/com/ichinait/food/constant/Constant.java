package com.ichinait.food.constant;

public class Constant {
	/**  数据状态，这里定义两个，其他状态需自定义*/
	public static final byte DATA_VALID_STATE = 1; //有效状态
	public static final byte DATA_INVALID_STATE = 0;//无效状态


	public static final byte PROJECT_STATUS_STARTED = 2;
	public static final byte PROJECT_STATUS_FINISH = 3;

	//正常
	public static final byte USER_STATUS_NORMAL = 1;
	//未审核
	public static final byte USER_STATUS_UNVERIFIED = 2;
	//审核不通过
	public static final byte USER_STATUS_VERIFIED = 3;



	
	public static final int EXEC_SUCCESS = 1;
	public static final int EXEC_FAILED = 0;
	public static final int EXEC_ERROR = -1;
	
	public static final int PAGE_LIMIT = 15;
	
	//原始数据保存路径
	public static final String PROPER_KEY_UPLOAD_PATH = "data.upload.path";
    public static final String PROPER_KEY_PROJECT_ATTACHMENT_UPLOAD_PATH = "project.attachment.upload.path";
    public static final String PROPER_KEY_INSTRUMENT_ATTACHMENT_UPLOAD_PATH = "instrument.attachment.upload.path";
    public static final String PROPER_KEY_ALGORITHM_ATTACHMENT_UPLOAD_PATH = "algorithm.attachment.upload.path";
    public static final String PROPER_KEY_STANDARD_ATTACHMENT_UPLOAD_PATH = "standard.attachment.upload.path";
    public static final String PROPER_KEY_COMMENT_ATTACHMENT_UPLOAD_PATH = "comment.attachment.upload.path";

	public static final String PROPER_KEY_ZIP_PATH = "zip.path";
	
	
	public static final String SELECT_BY_PRIMARY_KEY = "selectByPrimaryKey";
	public static final String DELETE_BY_PRIMARY_KEY = "deleteByPrimaryKey";
	public static final String INSERT = "insert";
	public static final String INSERT_SELECTIVE = "insertSelective";
	public static final String UPDATE_BY_PRIMARY_KEY = "updateByPrimaryKey";
	public static final String UPDATE_BY_PRIMARY_KEY_SELECTIVE = "updateByPrimaryKeySelective";

	//系统自带算法
	public  static final int SYS_ALGORITHM = 1;
	//自定义算法
	public  static final int CUSTOMER_ALGORITHM = 2;

	
	public static final String INSTRUMENT_CATEGORY = "1";
	public static final String INSTRUMENT_CATEGORY_AID = "2";
	

	//MSC
	public static final String MSC = "MSC";
	public static final String SMOOTHPF = "SMOOTHPF";
	public static final String SCALE = "SCALE";
	public static final String DERIV = "DERIV";//数据需要转置

}

