$(document).ready(function() {
    $("#login-form").validate({
        rules : {
            mail_address: {
                required: true
            },
            password: {
            	required: true
            }
        },
        messages: {
            mail_address: {
                required: "メールアドレスを入力してください。",
            },
        	password: {
        		required: "パスワードを入力してください。"
        	}
        }
    });
});


$.extend($.validator.messages, {
    email: '正しいメールアドレスの形式で入力して下さい',
  });