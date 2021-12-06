package projekt2MR;

import java.util.Date;

public class badanie {

private String waga, bmi;
private Date data;
	
	public badanie( Date data, String waga, String bmi) {
		this.data = data;
		this.waga = waga;
		this.bmi = bmi;
	}
	
    public Date getData() {
		return data;
	}
	
	public String getWaga() {
		return waga;
	}
	
	public String getBMI() {
		return bmi;
	}
}
