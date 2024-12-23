window.addEventListener('DOMContentLoaded', function () {
  let subSlider = new Swiper('.sub_section .swiper', {
    centeredSlides: true,
    slidesPerView: 2,
    spaceBetween: 30,
    loop: true,
    speed: 1500,
    autoplay: {
      delay: 2000,
      disableOnInteraction: false,
    },
    // breakpoints: {
    //   1024: {
    //     slidesPerView: 2.5,
    //     spaceBetween: 20,
    //   },
    //   768: {
    //     slidesPerView: 2,
    //     spaceBetween: 10,
    //   },
    //   480: {
    //     slidesPerView: 1,
    //     spaceBetween: 5,
    //   },
    // },
  });

  window.addEventListener('scroll', function () {
    let _scrollY = this.scrollY;

    if (_scrollY > 50) {
      header.classList.add('fixed');
    } else {
      header.classList.remove('fixed');
    }
  });

  const topBtn = this.document.querySelector('.top_btn');
  if (topBtn) {
    topBtn.addEventListener('click', function (e) {
      e.preventDefault();
      window.scrollTo({
        top: 0,
        behavior: 'smooth',
      });
    });
  }

  AOS.init({
    duration: 1200,
  });

  //클릭 이미지
  const fancyboxEl = this.document.querySelectorAll('.view_box');
  fancyboxEl.forEach((el) => {
    el.addEventListener('click', function (e) {
      Fancybox.bind('[data-fancybox]', {
        slidesToShow: 3,
        arrows: true,
      });
    });
  });

  // 지도
  let location = document.getElementById('staticMap');
  if (location) {
    let marker = {
      position: new kakao.maps.LatLng(36.35917, 127.387196),
      text: '상지국제특허법률사무소',
    };

    let options = {
      center: new kakao.maps.LatLng(36.35917, 127.387196),
      level: 4,
      marker: marker,
    };

    let map = new kakao.maps.StaticMap(location, options);

    // let markerPosition = new kakao.maps.LatLng(36.35917, 127.387196);

    // marker.setMap(map);
  }
});
