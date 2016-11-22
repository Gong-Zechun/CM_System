$(function(){
	$('.btn-submit').on('click',function(){
		var companyName=$('.ipt1').val();
		var contactName=$('.ipt2').val();
		var telNum=$('.ipt3').val();
		var wxNum=$('.ipt4').val();
		var cityName=$('.ipt5').val();
		if(companyName==''||contactName==''||telNum==''||wxNum==''||cityName==''){
			$('.shade').css({'display':'block'})
			$('.shade-box ul li').eq(0).css({'display':'block'}).stop().siblings().css({'display':'none'});
			$('.shade-box').css({'display':'block'});
		}else{
            $.ajax({
                type: "POST",
                url: "/petro/addNewPetroUser",
                dataType: "json",
                data: {
                    "companyName" : companyName,
                    "contactName" : contactName,
                    "telNum" : telNum,
                    "wxNum" : wxNum,
                    "cityName" : cityName
                },
                success: function(data) {
                    console.log(data);
                    if(data == 1){
                        $("#btnsure").on("click", function() {
                            window.location.reload();
                        });
                    }else{
                        alert("wrong data");
                    }
                },
                error: function(e) { //服务器响应失败处理函数
                    alert("用户信息提交失败");
                    console.log(e);
                }
            });
			$('.shade').css({'display':'block'})
			$('.shade-box ul li').eq(1).css({'display':'block'}).stop().siblings().css({'display':'none'})
			$('.shade-box').css({'display':'block'});
			$('.ipt1').val('');
			$('.ipt2').val('');
			$('.ipt3').val('');
			$('.ipt4').val('');
			$('.ipt5').val('');
		}
	})
	
	$('.btnsure').on('click',function(){
		$('.shade-box').css({'display':'none'});
		$('.shade').css({'display':'none'})
	})
})



//页面加载完成后执行动画
window.onload=function(){
	$('.sec-row img').addClass('active1');
	$('.sec-title').addClass('active2');
	$('section .container h1').addClass('active3');
	$('section .container p').addClass('active4');
}
