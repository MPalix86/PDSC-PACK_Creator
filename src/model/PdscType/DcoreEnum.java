package model.pdscType;

public enum DcoreEnum {
	SC000("SC000"),
	SC300("SC300"),
	
	Cortex_M0("Cortex-M0"),
	Cortex_M0_PLUS("Cortex-M0+"),
	Cortex_M1("Cortex-M1"),
	Cortex_M3("Cortex-M3"),
	Cortex_M4("Cortex-M4"),
	Cortex_M7("Cortex-M7"),
	
	Cortex_R4("Cortex-R4"),
	Cortex_R5("Cortex-R5"),
	
	Cortex_A5("Cortex-A5"),
	Cortex_A7("Cortex-A7"),
	Cortex_A8("Cortex-A8"),
	Cortex_A9("Cortex-A9"),
	
	Cortex_A15("Cortex-A15"),
	Cortex_A17("Cortex-A17"),
	Cortex_A53("Cortex-A53"),
	Cortex_A57("Cortex-A57"),
	Cortex_A72("Cortex-A72"),
	other("other");
	
//	SC000("SC000"),
//	SC300("SC300"),
//	SC000("SC000"),
//	SC300("SC300"),
//	SC000("SC000"),
//	SC300("SC300"),
	
	

	private final String core;

    private DcoreEnum(String core) {
        this.core = core;
    }

    public String getValue() {
        return this.core;
    }
}
