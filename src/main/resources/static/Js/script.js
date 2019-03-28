
/*function deshabilitaRetroceso(){
    window.location.hash="no-back-button";
    window.location.hash="Again-No-back-button" //chrome
    window.onhashchange=function(){window.location.hash="no-back-button";}
  
    
}*/

$(function($) {
 let url = window.location.href;
  $('nav ul li a').each(function() {
   if (this.href === url) {
   $(this).addClass('active');
  }
 });
});