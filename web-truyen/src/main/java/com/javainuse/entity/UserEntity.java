package com.javainuse.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_user")
public class UserEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String password;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "avatarId", referencedColumnName = "id")
 	private ImageEntity avatar;
	private Boolean enable;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date disableTo;
	@Column
	private Boolean enableComment;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date disableCommentTo;
	@Column
	private Boolean enablePostStory;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date disablePostStoryTo;
	private Boolean deleted;
}