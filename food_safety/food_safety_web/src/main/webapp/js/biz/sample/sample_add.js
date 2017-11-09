/**
 * Created by ichinait on 16-3-24.
 */
$(function(){
    $('input[name="productionDate"]').datepicker({
        dateFormat:'yy-mm-dd',
        changeYear:true,
        changeMonth:true,
        yearRange:"1980:2050"
    });
    $('input[type!="hidden"]').blur(function(){
        clearErrorInfo();
    });
});


/*    $( "input[name='scanningTime']" ).datetimepicker({
        dateFormat:'yy-mm-dd',
        timeFormat:'HH:mm:ss'
    });*/

    $('select[name="categoryLv1"]').change(function(){
       queryTypes(this.value,1);
    });


function saveSample(){
    clearErrorInfo();
    if(!validateForm()){
        return;
    }

    var sampleIndicators = [];
    $('#indicators .form-group').each(function(index,elem){
        var indicatorName = $(elem).find('input[name="indicatorName"]').val();
        var indicatorValue = $(elem).find('input[name="indicatorValue"]').val();
        var id = $(elem).find('input[name="indicatorId"]').val();
        var params = {
            indicatorName:indicatorName,
            indicatorValue:indicatorValue
        };
        if(id != null){
            params.id = id;
        }
        if(indicatorName.length >0 && indicatorValue.length >0){
            sampleIndicators.push(params);
        }
    });
    $('input[name="categoryLv1Id"]').val($('select[name="categoryLv1"]').val());
    $('input[name="categoryLv2Id"]').val($('select[name="categoryLv2"]').val());
    $('input[name="indicators"]').val($.toJSON(sampleIndicators));
    $('#saveBtn').addClass('disabled');
    $('#saveBtn').attr('disabled',true);

    var url = ctx + '/sample/save';
    $.ajax({
       url:url,
        data:$('#sampleForm').serialize(),
        type:'post',
        success:function(data){
            if(data.success == 1){
               //toListPage();
                setModal('提示','保存成功！',null);
            }
            $('#savebtn').removeClass('disabled');
            $('#savebtn').attr('disabled',false);

        },
        error:function(e){
            $('#saveBtn').removeClass('disabled');
            $('#saveBtn').attr('disabled',false);

            console.log(e);
        }
    });
}


function toListPage(){
    window.location.href = ctx + '/sample/toSamplePage';
}

function addParams(){
    var content = '<div class="form-group"><label for="indicatorName" class="col-sm-1 control-label">指标名称</label><div class="col-sm-5"><input type="text" class="form-control" name="indicatorName" placeholder="指标名称">'
        +'</div><label for="indicatorValue" class="col-sm-1 control-label">指标值</label><div class="col-sm-5"><input type="text" name="indicatorValue" class="form-control" placeholder="指标值"></div></div>';
    $('#indicators').append(content);
}

function delParams(){
    var id = $('#indicators .form-group:last').find('input[name="indicatorId"]').val();
    if(id != null){
        ajaxDelParams(id);
    }else{
        $('#indicators .form-group:last').remove();
    }
}

function ajaxDelParams(id){
    var url = ctx + '/sample/deleteIndicator';
    $.ajax({
        url:url,
        data:{'id':id},
        type:'post',
        success:function(o){
            $('#indicators .form-group:last').remove();
        },
        error:function(e){

        }
    });
}


function validateForm(){
    var text = '';
    var o = $('input[name="code"]');
    if($.trim($(o).val()).length == 0){
        text = '请输入样品编号';
        setError(o,text);
        return false;
    }
    o = $('input[name="name"]');
    if($.trim($(o).val()).length == 0){
        text = '请输入样品名称';
        setError(o,text);
        return false;
    }
    o = $('input[name="batches"]');
    if($.trim($(o).val()).length == 0){
        text = '请输入样品批次';
        setError(o,text);
        return false;
    }

  /*  if($('input[name="type"]').val().length == 0){
        text = '请输入样品类型';
        setError($('input[name="type"]'),text);
        return false;
    }*/

   o = $('input[name="producingArea"]');
    if($.trim($(o).val()).length == 0){
        text = '请输入样品产地';
        setError(o,text);
        return false;
    }
    o = $('input[name="productionDate"]');
    if($.trim($(o).val()).length == 0){
        text = '请输入生产日期';
        setError(o,text);
        return false;
    }
/*    o = $('input[name="scanningTime"]');
    if($.trim($(o).val()).length == 0){
        text = '请输入扫描日期';
        setError(o,text);
        return false;
    }*/
    o = $('input[name="manufactor"]');
    if($.trim($(o).val()).length == 0){
        text = '请输入生产商';
        setError(o,text);
        return false;
    }
    var indicatorName = true;
    var indicatorValue =true;
    $('input[name="indicatorName"]').each(function(){
       if($.trim(this.value).length ==0){
           text = '请输入指标名称';
           setError(this,text);
           indicatorName =false;
           return false;
       }
    });
    if(!indicatorName){
        return false;
    }

    $('input[name="indicatorValue"]').each(function(){
        if($.trim(this.value).length ==0){
            text = '请输入指标值';
            setError(this,text);
            indicatorValue = false;
            return false;
        }
    });
    if(!indicatorValue){
        return false;
    }

    
    return true;
}





