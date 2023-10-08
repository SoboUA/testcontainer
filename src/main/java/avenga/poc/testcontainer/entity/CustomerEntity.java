package avenga.poc.testcontainer.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("customers")
public class CustomerEntity {

    @Id
    private String id;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
}
