package com.gather.gathercommons.model;

import java.util.List;

public interface IComboBoxModel {

	public List<List<Object>> getOptions();

	public void setOptions(List<List<Object>> options);

	public Object getSelectedOption();

	public void setSelectedOption(Object option);
}
