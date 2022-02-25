package com.chberndt.liferay.model.listener.layout;

import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;

/**
 * @author Christian Berndt
 */
@Component(service = ModelListener.class)
public class LayoutModelListener extends BaseModelListener<Layout> {
	
	

}
