package com.engineer.proposition;

import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PropositionMapper {

    PropositionDTO toPropositionDTO(Proposition proposition);

    List<PropositionDTO> toPropositionDTO(Collection<Proposition> propositions);
}
