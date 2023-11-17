package com.mg.assignment2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="program")
public class Program {

	@Id
	private int programCode;
	private String ProgramName;
	private int duration;
	private double fee;
	
	public Program() {
		
	}

	public Program(int programCode, String programName, int duration, double fee) {
		super();
		this.programCode = programCode;
		ProgramName = programName;
		this.duration = duration;
		this.fee = fee;
	}

	public int getProgramCode() {
		return programCode;
	}

	public void setProgramCode(int programCode) {
		this.programCode = programCode;
	}

	public String getProgramName() {
		return ProgramName;
	}

	public void setProgramName(String programName) {
		ProgramName = programName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
}
