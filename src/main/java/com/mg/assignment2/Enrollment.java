package com.mg.assignment2;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="enrollment")
public class Enrollment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int applicationNo;
	@ManyToOne
	@JoinColumn(name="studentId")
	private Student studentId;
	@ManyToOne
	@JoinColumn(name="programCode")
	private Program programCode;
	private Date startDate;
	private double amountPaid;
	private boolean status;
	
	public Enrollment() {
		
	}

	public Enrollment(Student studentId, Program programCode, Date startDate, double amountPaid, boolean status) {
		super();
		this.studentId = studentId;
		this.programCode = programCode;
		this.startDate = startDate;
		this.amountPaid = amountPaid;
		this.status = status;
	}

	public int getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(int applicationNo) {
		this.applicationNo = applicationNo;
	}

	public Student getStudentId() {
		return studentId;
	}

	public void setStudentId(Student student) {
		this.studentId = student;
	}

	public Program getProgramCode() {
		return programCode;
	}

	public void setProgramCode(Program programCode) {
		this.programCode = programCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
