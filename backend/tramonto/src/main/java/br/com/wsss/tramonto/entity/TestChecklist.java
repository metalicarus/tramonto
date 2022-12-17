package br.com.wsss.tramonto.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.wsss.tramonto.entity.pk.TestChecklistPk;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TestChecklist {

    @EmbeddedId
    @JsonIgnore
    private TestChecklistPk pk;
}
