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

    // 검색타입 선택
    // const selectSearchType = document.querySelector('.sel_option select option');
    // if (selectSearchType) {
    //     selectSearchType.forEach(st => {
    //         st.addEventListener('click', function () {
    //
    //         })
    //     })
    // }


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

    const fileEl = document.getElementById('file_box');
    if (fileEl) {
        fileEl.addEventListener('change', function(event){
            handleFileChange(event);
        });

        const deleteBtn = document.getElementById('delete_file');
        if (deleteBtn) {
            deleteBtn.addEventListener('click', handleFileDelete);
        }
    }
});



function handleFileChange(event) {
    if(!event || !event.target) return;

    const fileInfo = document.getElementById('file_info');
    const fileName = document.getElementById('file_name');
    const file = event.target.files[0];
    if (file) {
        // 파일 크기 체크 (예: 10MB 이하)
        if (file.size > 10 * 1024 * 1024) {
            alert('첨부 파일 크기는 10MB를 초과할 수 없습니다.');
            event.target.value = '';
            return;
        }
        // 파일 확장자 체크 (예: jpg, jpeg, png)
        const allowedExtensions = ['pdf', 'doc', 'docx', 'xls', 'xlsx', 'jpg', 'jpeg', 'png'];
        const fileExtension = file.name.split('.').pop().toLowerCase();
        if (!allowedExtensions.includes(fileExtension)) {
            alert('허용되지 않는 파일 형식입니다.');
            event.target.value = '';
            return;
        }

        
        // 파일 이름이 너무 길 경우 축약
        const displayName = file.name.length > 30 ? file.name.substring(0, 27) + '...' : file.name;

        fileName.textContent = displayName;
        fileInfo.style.display = 'block';
    } else {
        fileInfo.style.display = 'none';
    }
        // 삭제 버튼 표시
        const deleteBtn = document.getElementById('delete_file');
        if (deleteBtn && file.name.length > 0) {
            deleteBtn.style.display = 'inline-block';
        }



}

function handleFileDelete() {
    const fileInfo = document.getElementById('file_info');
    const fileInput = document.getElementById('file_box');
    const fileName = document.getElementById('file_name');
    // 파일 입력 필드 초기화
    if(fileInput) fileInput.value = '';
    // 파일명 초기화
    if(fileName) fileName.textContent = '';
    // 파일 정보 숨김
    if(fileInfo) fileInfo.style.display = 'none';
}



function isContains(target, chars) {

    for (var i = 0; i < target.value.length; i++) {
        if (chars.indexOf(target.value.charAt(i)) != -1) return true;
    }
    return false;
}
