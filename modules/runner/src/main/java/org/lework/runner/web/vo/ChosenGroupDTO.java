package org.lework.runner.web.vo;

import org.lework.runner.utils.Collections3;

import java.util.ArrayList;
import java.util.List;

/**
 * jQuery Chosen plugins Group DTO
 * User: Gongle
 * Date: 13-11-20
 */
public class ChosenGroupDTO {

    public ChosenGroupDTO(String label, List<ChosenDTO> chosenDTOs) {
        this.label = label;
        this.chosenDTOs = chosenDTOs;
    }

    private String label;
    private List<ChosenDTO> chosenDTOs = new ArrayList<ChosenDTO>();

    private boolean getHasChild() {
        return Collections3.isNotEmpty(chosenDTOs);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ChosenDTO> getChosenDTOs() {
        return chosenDTOs;
    }

    public void setChosenDTOs(List<ChosenDTO> chosenDTOs) {
        this.chosenDTOs = chosenDTOs;
    }
}
