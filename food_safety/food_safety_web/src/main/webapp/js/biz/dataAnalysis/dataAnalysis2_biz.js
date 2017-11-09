/**
 * 2017年7月11日10:17:04 
 */

$(function() {
	alert($("#pds").val());
});

function showFormulae(){
	$("#pds").on('click',function(){
		$(this).show();
	})
}
