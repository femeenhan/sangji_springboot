window.addEventListener('DOMContentLoaded', function (message) {
    const loginBtnEl = document.querySelector('.header_btn_wrap .login');

    document.querySelector('input[name=id]')?.focus();
    document.querySelector('input[name=person_name]')?.focus();

    // if (loginBtnEl) {
    //     loginBtnEl.addEventListener('click', function (e) {
    //         e.preventDefault();
    //
    //     })
    // }
    // loginBtnEl.click();

    // 로그인하기
    const loginEl = document.getElementById('memberLoginBtn');
    if (loginEl) {
        loginEl.addEventListener('click', function () {
            const idEl = document.querySelector('.login_info input[name=id]');
            const pwEl = document.querySelector('.login_info input[name=pass]');
            const id = idEl.value.trim();
            const pw = pwEl.value.trim();

            if (id.length <= 0) {
                alert('아이디를 입력하세요');
                idEl.focus();
            } else if (pw.length <= 0) {
                alert('비밀번호를 입력하세요');
                pwEl.focus();
            } else {
                if (id === 'admin' && pw === '1') {
                    location.href = '/';
                } else {
                    alert('가입정보가 없습니다.');
                    idEl.focus();
                }
            }
        })
    }

    // 아이디, 비밀번호 엔터키 이벤트 (로그인)
    const idEnter = document.querySelector('.login_info input[name=id]');
    const passEnter = document.querySelector('.login_info input[name=pass]');

    if (idEnter) {
        idEnter.addEventListener('keyup', function (e) {
            if (e.key === 'Enter') document.getElementById('memberLoginBtn').click();
        })
    }
    if (passEnter) {
        passEnter.addEventListener('keyup', function (e) {
            if (e.key === 'Enter') document.getElementById('memberLoginBtn').click();

        })
    }

    // 아이디 찾기 실행
    const nameEl = document.querySelector('.login_find_id .login_info input[name=person_name]');
    const emailEl = document.querySelector('.login_find_id .login_info input[name=email]');
    const idFindEl = document.getElementById('findIdBtn');

    if (idFindEl) {
        idFindEl.addEventListener('click', function (e) {
            const name = nameEl.value.trim();
            const email = emailEl.value.trim();
            console.log(nameEl.value);
            // e.preventDefault();
            if (name === 'han' && email === 'han') {
                const foundIdEl = document.querySelector('.login_find_id_ok .found_id strong');
                if (foundIdEl) {
                    foundIdEl.innerHTML = 'test';
                }
            }
        })
    }

    if (idFindEl) {
        idFindEl.addEventListener('keyup', function (e) {
            if (e.key === 'Enter') document.getElementById('findIdBtn').click();
        });
    }

    // join 아이디 중복확인
    const joinEl = document.getElementById("join")
    if (joinEl) {
        const checkBtn = document.getElementById("id_check");
        const idEl = document.getElementById("input_text_id");
        checkBtn.addEventListener("click", function (e) {
            e.preventDefault();
            const id = idEl.value;
            if (id.length == 0) {
                alert('아이디를 입력하세요');
                idEl.focus();
            }
            fetch(`/rest/idCheck/${id}`, {
                method: 'GET',
            }).then(res => {
                // 요청 성공
                if (res.ok) {
                    return res.json();
                }
            }).then(result => {
                if (result == 0) {
                    if (!confirm(result.message + '사용하시겠습니까?')) {
                    }
                } else {
                    alert(result.message);
                }
            })
        })
    }
});

