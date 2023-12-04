package com.kh.springdb.vo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="STUDENT_MEMBER")
public class STmember {

	//JPA를 통해 직접 데이터 생성 및 조작
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="st_Seq")
	@SequenceGenerator(name="st_Seq", sequenceName="st_Seq", allocationSize=1)
	private Long STNumber;
	private String memeberName;
	private int koreanScore;
	private int englishScore;
	private int mathScore;
}
