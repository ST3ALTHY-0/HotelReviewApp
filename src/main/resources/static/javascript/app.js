$(document).ready(function() {
			// console.log('Hotel:', hotels[1]);

	// Watch dropdowns and notify when selection changes.
	const dropdownIds = ['#country', '#region', '#city', '#hotel'];

	dropdownIds.forEach(function(selector) {
		$(document).on('change', selector, function() {
			const field = selector.replace('#', '');
			const value = $(this).val();
			console.log('Selection changed:', field, value);

			$(document).trigger('hotelFilterChanged', { field: field, value: value });
		});
	});

	$(document).on('hotelFilterChanged', function(evt, data) {
		const info = '<p class="text-muted">Filter changed: ' + data.field + ' = ' + (data.value || '(any)') + '</p>';
		// append or replace a small status area in results
        //depending on the filter changed change whats shown in other filters
		$('#results').html(info);
	});

});

//Mainly what we are doing here is doing a bit of CSR
/*
We have a list of hotels in thymeleaf
So when a user selects some option from the list, say USA in country,
we will restrict what regions, cities, hotel names we can see in the other
drop downs. This file currently only reports selection events; later we can
add filtering logic that updates other selects or performs a server request.

*/