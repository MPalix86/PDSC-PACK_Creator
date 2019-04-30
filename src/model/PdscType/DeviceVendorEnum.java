package model.PdscType;
	public enum DeviceVendorEnum {

		ABOV_Semiconductor_126("ABOV Semiconductor:126"),
		Actel_56("Actel:56"),
		TMicroelectronics_13("STMicroelectronics:13");

		private final String vendorName;

	    private DeviceVendorEnum(String vendorName) {
	        this.vendorName = vendorName;
	    }

	    public String getValue() {
	        return this.vendorName;
	    }
	}