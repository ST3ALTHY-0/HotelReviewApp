//Mainly what we are doing here is doing a bit of CSR
/*
We have a list of hotels in thymeleaf
So when a user selects some option from the list, say USA in country,
we will restrict what regions, cities, hotel names we can see in the other
drop downs. 

We also get hotelRecord data (hotel name and a map of ratingCategory/normalized polarity)
from our api in controllers/restControllers/APIController.java and update our page with
the results
*/

$(document).ready(function () {
  const dropdownIds = ['#country', '#region', '#city', '#hotel'];

  const toKey = (v) => (v ? String(v).trim().toLowerCase() : '');

  //filter hotels based on currently selected country, region, and city
  const getFilteredHotels = () => {
    const selCountry = toKey($('#country').val());
    const selRegion = toKey($('#region').val());
    const selCity = toKey($('#city').val());
    const out = [];
    const all = hotels || [];

    for (let i = 0; i < all.length; i++) {
      const h = all[i];
      const hc = toKey(h.country || h.countryname || h.countryName);
      const hr = toKey(h.region || h.regionname || h.regionName);
      const city = toKey(h.city || h.cityname || h.cityName);

      if (selCountry && hc !== selCountry) continue;
      if (selRegion && hr !== selRegion) continue;
      if (selCity && city !== selCity) continue;

      out.push(h);
    }
    return out;
  };

  //make sure values are unique so we dont have duplicate countries for each hotel and we sort values
  //we have to do this because we are only passing hotels into JS instead of all of the sets we pass through
  //thymeleaf, could/should change this but it works as is sooooo idc
  const uniqueSorted = (values) => {
    const seen = {};
    for (let i = 0; i < values.length; i++) {
      const v = values[i];
      if (v == null) continue;
      const s = String(v).trim();
      if (s === '') continue;
      seen[s] = true;
    }
    const list = Object.keys(seen);
    list.sort();
    return list;
  };

  //rebuild a dropdown with given values
  const rebuildSelect = (sel, values, preserve) => {
    const $s = $(sel);
    const cur = preserve ? ($s.val() || '') : '';
    $s.empty().append($('<option>').val('').text('Any'));

    // Add each option
    values.forEach(v => $s.append($('<option>').val(v).text(v)));

    if (preserve && cur) {
      const match = $s.find('option').filter(function () {
        return $(this).val().toLowerCase() === cur.toLowerCase();
      }).first();
      $s.val(match.length ? match.val() : '');
    } else {
      $s.val('');
    }

    // log options to catch stupid bug
    try {
      var printed = values.slice(0, 50);
      console.log('Updated ' + sel + ' options (count=' + values.length + '):', printed);
    } catch (e) {
      console.log('Updated ' + sel + ' options (count=' + values.length + ')');
    }
  };

    // main refresh function: updates all dropdowns based on selections
  const refresh = (changed) => {
    // reset dependent dropdowns if a parent changed
    if (changed === '#country') {
      $('#region, #city, #hotel').val('');
    } else if (changed === '#region') {
      $('#city, #hotel').val('');
    } else if (changed === '#city') {
      $('#hotel').val('');
    }

    const filtered = getFilteredHotels();
    let regions = [], cities = [], hotelsList = [];

    filtered.forEach(h => {
      regions.push(h.region || h.regionname || h.regionName);
      cities.push(h.city || h.cityname || h.cityName);
      hotelsList.push(h.hotel || h.hotelname || h.hotelName);
    });

    regions = uniqueSorted(regions);
    cities = uniqueSorted(cities);
    hotelsList = uniqueSorted(hotelsList);

    const search = toKey($('#hotelSearch').val());
    if (search) {
      hotelsList = hotelsList.filter(h => h.toLowerCase().includes(search));
    }

    const limited = hotelsList.slice(0, 50);

    rebuildSelect('#region', regions, true);
    rebuildSelect('#city', cities, true);
    rebuildSelect('#hotel', limited, true);

    $('#hotel').next('.limited-note').remove();
    if (hotelsList.length > limited.length) {
      $('#hotel').after(
        `<div class="small text-muted limited-note">
        Showing ${limited.length} of ${hotelsList.length} hotels — narrow filters to see more.
      </div>`
      );
    }
  };

  //adding handlers to all the dropdowns and we are passing which one changed
  dropdownIds.forEach(sel => {
    $(document).on('change', sel, function () {
      refresh(sel);
    });
  });

  //trigger refresh on search input
  $(document).on('input', '#hotelSearch', () => refresh());
  refresh();
});

// Form submission handler
$('#searchForm').on('submit', function (e) {
  e.preventDefault();
  $.getJSON('/api/hotels/search/rating', $(this).serialize(), function (payload) {
    const results = payload ? (Array.isArray(payload) ? payload : [payload]) : [];
    if (!results.length) {
      $('#results').html('<p>No results found.</p>');
      return;
    }
    //build HTML for each hotel record with ratings
    const html = results.map(r => {
      const name = (r.hotelAddress && r.hotelAddress.hotel) || r.hotelName || r.hotel || '';
      const loc = r.hotelAddress ? [r.hotelAddress.city, r.hotelAddress.region].filter(Boolean).join(', ') : '';
      const country = (r.hotelAddress && r.hotelAddress.country) || r.country || '';
      const ratings = r.rating ? Object.entries(r.rating).map(([k, v]) => `${k}: ${v}/5`).join(' · ') : '';
      return `<div class="mb-3">
                <h5>${name}</h5>
                <p>${loc}</p>
                <p>${country}</p>
                <div>${ratings}</div>
                <hr/>
              </div>`;
    }).join('');

    $('#results').html(html);
  }).fail(function (xhr) {
    const msg = xhr.responseJSON?.message || xhr.statusText;
    $('#results').html('<p class="text-danger">Search failed: ' + msg + '</p>');
  });
});



