package br.com.wsss.tramonto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.wsss.tramonto.domain.type.Approval;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TEST_VECTORS")
@Getter
@Setter
public class TestVector extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6169639783369747444L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEST_ID")
	private Test test;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY", referencedColumnName = "ID", nullable = false)
	private VectorCategory category;

	@Enumerated(EnumType.STRING)
	@Column(name = "APPROVAL")
	private Approval approval;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OWNER", referencedColumnName = "ID", nullable = false)
	private User owner;

	private String title;
	private String description;
	private String expectedResults;
	private String resultsObtained;
	private String observationApproval;
	private Integer reproducibility;
	private Integer impact;
	
	@Override
	public boolean equals(Object o) {
		TestVector tv = (TestVector) o;
		if (test == null)
			return title.equals(tv.getTitle()) && description.equals(tv.getDescription());
		return title.equals(tv.getTitle()) && 
				description.equals(tv.getDescription()) &&
				test.getId().equals(tv.getTest().getId());
	}
}
