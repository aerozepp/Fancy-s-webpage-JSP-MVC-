//signin popup appearing

$(document).ready(function(){
	$('.signin_popup').click(function(){
		$('#signin_popup .container').css('display', 'inline-block');
		$('#cover, #menu, #music, #tour, #footer').css('filter', 'brightness(30%)');
	});
});

//signup popup appearing

$(document).ready(function(){
	$('#signup_btn').click(function(){
		$('#signup_popup .container').css('display', 'inline-block');
		$('#cover, #menu, #music, #tour, #footer').css('filter', 'brightness(30%)');
	});
});

//forum
$(document).ready(function(){
	$('.forum').click(function(){
		$('#forum_popup .container').css('display','inline-block');
		$('#cover, #menu, #music, #tour, #footer').css('filter', 'brightness(30%)');
	});

});


//create message popup
$(document).ready(function(){
	$('.create').click(function(){
		$('#message_popup .container').css('display','inline-block');
		$('#forum_menu, #forum_page, #comment_box, #footer').css('filter', 'brightness(30%)');
		$('body').addClass('stop-scrolling');
	});

});

//edit message
$(document).ready(function(){
	$('.likes_box .lnr-pencil').click(function(){
		$('#message_edit_popup .container').css('display','inline-block');
		$('#forum_menu, #forum_page, #comment_box, #footer').css('filter', 'brightness(30%)');
		$('body').addClass('stop-scrolling');
	});

});

//delete popup

$(document).ready(function(){
	$('.likes_box .lnr-trash').click(function(){
		$('#delete_popup .container').css('display','inline-block');
		$('#forum_menu, #forum_page, #comment_box, #footer').css('filter', 'brightness(30%)');
		$('body').addClass('stop-scrolling');
	});

});


$(document).ready(function(){

	$('#comment_box .likes_box span').click(function(){
		$(this).animate({fontSize : '19px'}, 300)
		.animate({fontSize : '15px'}, 300).css('color', 'red');
	});
});

//comment_box popup

/*$(document).ready(function(){

	$('.content').click(function(){
		$('#forum_menu, #forum_page, #footer').css('filter', 'brightness(30%)');
	});

});*/


//available box

$(document).ready(function(){
	$('.buying').mouseover(function(){
		$('.buying p a').css('color','#FEF200');
	}).mouseout(function(){
		$('.buying p a').css('color','#323C3C');
	});
});


//footer's link opacity effect
$(document).ready(function(){

	$('footer li').mouseover(function(){
		$(this).addClass('mouseover');	
		$('footer li:not(.mouseover)').css("opacity","0.3");
		}).mouseout(function(){
		$(this).removeClass('mouseover');
		$('footer li:not(.mouseover)').css("opacity","1");	
	});
});

/*smooth scroll*/
$(function() {
  $('a[href*="#"]:not([href="#"])').click(function() {
    if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
      if (target.length) {
        $('html, body').animate({
          scrollTop: target.offset().top
        }, 500);
        return false;
      }
    }
  });
});


//responsive logo to signout

$(document).ready(function(){
	$('#signin_box .profile_img').click(function(){
		if (	$('#signin_box .signout_popup').is(":hidden") ){
			$('#signin_box .signout_popup').animate({padding: "+=3px"}, 300).slideDown();
		}else{
			$('#signin_box .signout_popup').animate({padding: "-=3px"}, 700).hide();
		}
	});
});

//responsive menu toggle

$(document).ready(function(){
	$('#cover .lnr-menu').click(function(){
		if ($('#menu .responsive_menu').is(":hidden") ){
			$('#menu .responsive_menu').slideDown();
		}else{
			$('#menu .responsive_menu').hide();
		}
	});
});
