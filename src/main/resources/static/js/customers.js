window.addEventListener('DOMContentLoaded', function () {
  const tabMenuEl = document.querySelectorAll('.tab_menu a');
  const tabBodyEl = document.querySelectorAll('.tab_body');
  const tabNameMapping = {
    특허: 'patent',
    디자인: 'design',
    상표: 'brand',
  };

  if (tabMenuEl) {
    // 각 탭에 클릭이벤트 추가
    tabMenuEl.forEach((tab) => {
      tab.addEventListener('click', function (e) {
        e.preventDefault();
        // 클릭된 탭에서 active 클라스가 없으면 모든 탭에서 active 제거하고
        tabMenuEl.forEach((t) => t.classList.remove('active'));
        // 클릭된 탭에 active 추가
        tab.classList.add('active');

        // 클릭된 탭에 해당하는 바디 표시
        const tabName = tab.textContent.trim();
        const targetId = tabNameMapping[tabName];

        // 모든 탭바디의 active 제거
        tabBodyEl.forEach((body) => body.classList.remove('active'));

        // 선택된 탭바디에 active 추가
        const selectedBodyId = document.getElementById(targetId);
        selectedBodyId.classList.add('active');
      });
    });
  }

  window.addEventListener('scroll', function () {
    let _scrollY = this.scrollY;

    if (_scrollY > 50) {
      header.classList.add('fixed');
    } else {
      header.classList.remove('fixed');
    }
  });

  // 검색창 옵션 선택
  const selOptionEl = document.querySelector('.sel_option');
  if (selOptionEl) {
    selOptionEl.addEventListener('click', function () {
      selOptionEl.classList.toggle('on');
    });
  }

  // 이메일 선택
  const domainEl = document.querySelector('.input_wrap select[name=domain]');
  if (domainEl) {
    domainEl.addEventListener('change', function () {
      let domainVal = this.value;
      let emailEl = document.querySelector('.input_wrap input[name=email]');
      // console.log(emailEl);
      // console.log(emailEl.value);
      if (isContains(emailEl, '@')) {
        emailEl.value = emailEl.value.substring(0, emailEl.value.indexOf('@'));
      }
      emailEl.value += '@' + domainVal;
      // console.log(emailEl.value);
      if (!domainVal) emailEl.focus();
    });
  }

  const topBtn = document.querySelector('.top_btn');
  if (topBtn) {
    topBtn.addEventListener('click', function (e) {
      e.preventDefault();
      window.scrollTo({
        top: 0,
        behavior: 'smooth',
      });
    });
  }
});

function isContains(target, chars) {
  for (var i = 0; i < target.value.length; i++) {
    if (chars.indexOf(target.value.charAt(i)) != -1) return true;
  }
  return false;
}
