//focus
$(function(){
	$('input:visible').first().focus();
});

$(function() {
	$('#index_header').load('index_header.html');
});

$(function() {
 $('#header').load('header.html');
});

//スクローる
$(function(){
	$('a[href^=#]').click(function(){
		var speed = 500;
		var href= $(this).attr("href");
		var target = $(href == "#" || href == "" ? 'html' : href);
		var position = target.offset().top;
		$("html, body").animate({scrollTop:position}, speed, "swing");
		return false;
	});
});

//日付取得

//14日後の取得
$(function(){
	var now = new Date(); //現在の日付
	
	//現在
	var month = now.getMonth()+1;
	var date = now.getDate();
	
	
	var nowms = now.getTime(); //現在の日付をミリ秒単位に変換 
	var after = 14; //何日後かを入れる
	after = after*24*60*60*1000; //ミリ秒に変換
	ans = new Date(nowms+after); //現在＋何日後 のミリ秒で日付オブジェクト生成
	m=ans.getMonth() + 1;
	d=ans.getDate();
	w=ans.getDay();//曜日
	$('.day').text( month + '月' + date + '日から' + m + '月' + d + '日までの貸し出しになります。');
});


//alert
$(function(){
	var now = new Date();
	var month = now.getMonth()+1;
	var date = now.getDate();
	var nowms = now.getTime(); //現在の日付をミリ秒単位に変換 
	var after = -1; //何日後かを入れる
	after = after*24*60*60*1000; //ミリ秒に変換
	ans = new Date(nowms+after); //現在＋何日後 のミリ秒で日付オブジェクト生成
	m=ans.getMonth() + 1;
	d=ans.getDate();
	w=ans.getDay();//曜日
	$('.alertday').text( m + '月' + d + '日' );
});


//ajax
//search
$(function() {
    $('#ajax-btn').click(function() {
        $.ajax({
               type: 'POST',
               url: 'search.html',
               dataType: 'html',
               success: function(data) {
                   $('#sub-view').html(data);
               },
               error:function() {
                   alert('問題がありました。');
               }
        });
    });
});

//search-list
$(function() {
    $('#next-list').click(function() {
        $.ajax({
               type: 'POST',
               url: 'search-two.html',
               dataType: 'html',
               success: function(data) {
                   $('#search-result').html(data);
               },
               error:function() {
                   alert('問題がありました。');
               }
        });
    });
});

//search-list-two
$(function() {
    $('#former-list-two').click(function() {
        $.ajax({
               type: 'POST',
               url: 'search.html',
               dataType: 'html',
               success: function(data) {
                   $('#search-result').html(data);
               },
               error:function() {
                   alert('問題がありました。');
               }
        });
    });
});

//user-lend-history
$(function() {
    $('#former-lend-history').click(function() {
        $.ajax({
               type: 'POST',
               url: 'lend-history-two.html',
               dataType: 'html',
               success: function(data) {
                   $('#lend-main').html(data);
               },
               error:function() {
                   alert('問題がありました。');
               }
        });
    });
});

$(function() {
    $('#next-lend-history-two').click(function() {
        $.ajax({
               type: 'GET',
               url: 'lend-history.html',
               dataType: 'html',
               success: function(data) {
                   $('#lend-main').html(data);
               },
               error:function() {
                   alert('問題がありました。');
               }
        });
    });
});


//スクロール
$(function() {
 
    //ページ内スクロール
    $("#ajax-btn").click(function () {
        var i = $("#ajax.btn").index(this)
        var p = $("#sub-view").eq(i).offset().top;
        $('html,body').animate({ scrollTop: p }, 'fast');
        return false;
    });
  
});