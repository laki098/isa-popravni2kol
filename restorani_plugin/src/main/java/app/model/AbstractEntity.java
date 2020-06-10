package app.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, columnDefinition = "BIGINT")
    private Long id;
	
	@Column(nullable = false)
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public AbstractEntity(Long id, Integer version) {
		super();
		this.id = id;
		this.version = version;
	}

	public AbstractEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

    

}
