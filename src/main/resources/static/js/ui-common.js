window.addEventListener("DOMContentLoaded", function () {
  window.addEventListener("load", () => {
    const allElements = document.getElementsByTagName("*");
    Array.prototype.forEach.call(allElements, (el) => {
      const includePath = el.dataset.includePath;
      if (includePath) {
        fetch(includePath)
          .then((resp) => {
            if (resp.ok) {
              return resp.text();
            } else {
              return null;
            }
          })
          .then((data) => {
            if (data) {
              el.outerHTML = data;
            }
          });
      }
    });
  });

  let mainSlider = new Swiper(".main_slider .swiper", {
    simulateTouch: true,
    loop: true,
    effect: "fade",
    speed: 1500,
    autoplay: {
      delay: 5000,
      disableOnInteraction: false,
    },

    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },

    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
  });

  // document
  //   .querySelector('.main_slider .toggle_btn')
  //   .addEventListener('click', function () {
  //     this.classList.toggle('on');

  //     if (this.classList.contains('on')) {
  //       mainSlider.autoplay.stop();
  //     } else {
  //       mainSlider.autoplay.start();
  //     }
  //   });

  // 헤더 스크롤시 스타일 변경
  window.addEventListener("scroll", function () {
    let _scrollY = this.scrollY;

    if (_scrollY > 50) {
      header.classList.add("fixed");
    } else {
      header.classList.remove("fixed");
    }
  });

  // 헤더 언어 클릭시 변경
  const langChoice = document.querySelector(".btn_wrap .lang_wrap .lang_btn");
  if (langChoice) {
    langChoice.addEventListener("click", function () {
      langChoice.innerText = langChoice.innerText === "ENG" ? "한국어" : "ENG";
    });
  }

  // 탑버튼 이동
  const topBtn = this.document.querySelector(".top_btn");
  if (topBtn) {
    topBtn.addEventListener("click", function (e) {
      e.preventDefault();
      window.scrollTo({
        top: 0,
        behavior: "smooth",
      });
    });
  }

  // 헤더 사이트맵
  // const sitemapBtn = document.querySelector('#header .sitemap_btn');
  // if (sitemapBtn) {
  //   sitemapBtn.addEventListener('click', function () {
  //     this.classList.toggle('on');
  //     document.querySelector('#header .menu_wrap').classList.toggle('on');
  //     document
  //       .querySelector('#header .lang_wrap .lang_btn')
  //       .classList.toggle('on');
  //     document.body.classList.toggle('on');
  //   });
  // }

  // aos 초기화
  AOS.init({
    duration: 1200,
  });
});
