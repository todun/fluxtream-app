package org.fluxtream.core.api;

import java.util.ArrayList;
import java.util.List;
import org.fluxtream.core.auth.AuthHelper;
import org.fluxtream.core.connectors.Connector;
import org.fluxtream.core.connectors.ObjectType;
import org.fluxtream.core.domain.AbstractFacet;
import org.fluxtream.core.domain.AbstractRepeatableFacet;
import org.fluxtream.core.domain.ApiKey;
import org.fluxtream.core.metadata.AbstractTimespanMetadata;
import org.fluxtream.core.mvc.models.TimeBoundariesModel;
import org.fluxtream.core.services.ApiDataService;
import org.fluxtream.core.services.BuddiesService;
import org.fluxtream.core.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalendarDataHelper {

	@Autowired
	private ApiDataService apiDataService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private BuddiesService buddiesService;

	/**
	 * This is to let the client discard responses that are coming "too late"
	 * 
	 */
	TimeBoundariesModel getStartEndResponseBoundaries(AbstractTimespanMetadata dayMetadata) {
		TimeBoundariesModel tb = new TimeBoundariesModel(dayMetadata.start, dayMetadata.end);
		return tb;
	}

    public List<AbstractFacet> getFacets(Connector connector,
                                         ObjectType objectType,
                                         long guestId,
                                         List<String> dates, Long updatedSince) {
        List<AbstractFacet> facets = new ArrayList<AbstractFacet>();
        try {
            if (AuthHelper.isViewingGranted(connector.getName(), guestId, buddiesService)) {
                final ApiKey apiKey = guestService.getApiKey(guestId, connector);
                facets = apiDataService.getApiDataFacets(apiKey, objectType,
                        dates, updatedSince);
                facets = buddiesService.filterFacets(AuthHelper.getGuestId(), apiKey.getId(), facets);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return facets;
    }

	public List<AbstractFacet> getFacets(Connector connector,
			ObjectType objectType, long guestId, AbstractTimespanMetadata timespanMetadata, Long updatedSince) {
		List<AbstractFacet> facets = new ArrayList<AbstractFacet>();
		try {
            if (AuthHelper.isViewingGranted(connector.getName(), guestId, buddiesService)) {
                final ApiKey apiKey = guestService.getApiKey(guestId, connector);
                facets = apiDataService.getApiDataFacets(apiKey, objectType, timespanMetadata.getTimeInterval(), updatedSince);
                facets = buddiesService.filterFacets(AuthHelper.getGuestId(), apiKey.getId(), facets);
            }
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return facets;
	}

    public List<AbstractRepeatableFacet> getFacets(final Connector connector,
                                                   final ObjectType objectType,
                                                   final long guestId,
                                                   final String startDate,
                                                   final String endDate, Long updatedSince) {
        List<AbstractRepeatableFacet> facets = new ArrayList<AbstractRepeatableFacet>();
        try {
            if (AuthHelper.isViewingGranted(connector.getName(), guestId, buddiesService)) {
                final ApiKey apiKey = guestService.getApiKey(guestId, connector);
                facets = apiDataService.getApiDataFacets(apiKey, objectType,
                        startDate, endDate, updatedSince);
                facets = buddiesService.filterFacets(AuthHelper.getGuestId(), apiKey.getId(), facets);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return facets;
    }
}
