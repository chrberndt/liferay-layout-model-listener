package com.chberndt.liferay.model.listener.layout;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.sites.kernel.util.Sites;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Christian Berndt
 */
@Component(service = ModelListener.class)
public class LayoutModelListener extends BaseModelListener<Layout> {

	@Override
	public void onBeforeCreate(Layout layout) throws ModelListenerException {
		try {
			Group group = _groupGroupLocalService.getGroup(layout.getGroupId());

			String friendlyURL = group.getFriendlyURL();

			if ((friendlyURL != null) && friendlyURL.startsWith("/template-")) {

				// Reverse layoutUpdateable default value only
				// in the realm of site templates

				UnicodeProperties typeSettingsUnicodeProperties =
					layout.getTypeSettingsProperties();

				typeSettingsUnicodeProperties.setProperty(
					Sites.LAYOUT_UPDATEABLE, Boolean.FALSE.toString());
			}
		}
		catch (Exception exception) {
			throw new ModelListenerException(exception);
		}
	}

	@Reference
	private GroupLocalService _groupGroupLocalService;

}