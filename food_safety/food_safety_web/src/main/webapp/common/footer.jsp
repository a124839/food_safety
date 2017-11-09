<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<footer class="main-footer">
	<div class="pull-right hidden-xs">
		<b>Version</b> 1.0
	</div>
	<strong>Copyright &copy; 2010-2016 <a
		href="http://www.ichinait.com/">北京华信共达科技有限责任公司</a>.
	</strong> All rights reserved.
</footer>

<!-- 模态框（Modal） -->
<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel"></h4>
			</div>
			<div class="modal-body" id="modalBody"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" id="modalConfirm" data-dismiss="modal">确认</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
//自定义模态框
function setModal(title,body,callback){

	$("#myModalLabel").text(title);
	$("#modalBody").html(body);
	$('#myModal').modal('show');
	//绑定之前先移除
	$('#modalConfirm').unbind();
	$("#modalConfirm").click(function(){
		if(callback != null){
			callback();
		}
		//$('#myModal').modal('hide');
	});
}
/* $('#myModal').on('hide.bs.modal', function () {
	  $('.modal-backdrop.fade.in').remove();
	  console.log('modal hide ...');
	}) */
</script>