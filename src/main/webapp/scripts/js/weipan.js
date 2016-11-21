$(function(){
	$('.btn-submit').on('click',function(){
		var nameText=$('.ipt1').val()
		var contactText=$('.ipt2').val()
		var telText=$('.ipt3').val()
		var wxText=$('.ipt4').val()
		var cityText=$('.ipt5').val()
		if(nameText==''||telText==''||wxText==''||cityText==''||contactText==''){
			$('.shade').css({'display':'block'})
			$('.shade-box ul li').eq(0).css({'display':'block'}).stop().siblings().css({'display':'none'});
			$('.shade-box').css({'display':'block'});
		}else{
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
