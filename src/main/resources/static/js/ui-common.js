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
    const langChoice = document.querySelector(".header_btn_wrap .lang_wrap .lang_btn");
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

    // 웹 에디터가 되기위해 editor 속성 찾기
    const editor = document.querySelector('[data-editor]');
    if (editor) {
        // 해당 속성 숨기기
        editor.style.display = 'none';

        // ID값 얻기
        const id = editor.getAttribute('id');
        // 데이터 VALUE 얻기
        const body = editor.dataset.editor;

        // 웹 에디터 생성함수 호출
        quill(id, body);
    }

    // 웹 에디터 생성 함수
    function quill(id, body) {
        // div 태크 생성
        const template = document.createElement('div');
        // 속성, style, body 추가
        template.setAttribute('id', 'editor-container');
        template.setAttribute('style', 'width: 80%; height: 300px;');
        template.innerHTML = body;

        // 기존 태그뒤에 웹에디터 추가
        document.getElementById(id).after(template);

        // 웹 에디터 객체 생성
        const quill = new Quill('#editor-container', {
            placeholder: '내용을 입력하세요',
            theme: 'snow',
        });

        // 서버에 전송을 위해 기존 태그에 추가된 내용을 붙이기
        quill.on('text-change', function () {
            document.getElementById(id).value = quill.root.innerHTML;
        });
    }

    // pagination active
    const pagingEl = document.querySelector('.pagination .page_num');
    if (pagingEl) {
        pagingEl.addEventListener('click', function (e) {
            e.preventDefault();
            pagingEl.classList.add('.active');
        })
    }

});
