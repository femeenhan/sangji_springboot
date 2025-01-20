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


    // ----- 회원가입 하기

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

    // 이메일 주소 Select
    const domainEl = document.getElementById('email_domain');
    if (domainEl) {
        domainEl.addEventListener('change', function () {
            const emailInput = document.getElementById('email_address');
            const selectedDomain = this.value;
            if (emailInput !== '') {
                emailInput.value = '@' + selectedDomain;
            } else if (selectedDomain === '직접입력') {
                emailInput.value = '';
                emailInput.focus();
            } else {
                emailInput.value = '';
            }
        })
    }

    // 생년월일 Select
    flatpickr("#datepicker", {
        locale: "ko",
        enableTime: false,
        dateFormat: "Y-m-d",
        maxDate: "today",
    })

    // postcode API
    function postcode() {
        new daum.Postcode({
            oncomplete: function (data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraRoadAddr += extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName;
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraRoadAddr !== '') {
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode;
                document.getElementById('address1').value = roadAddr;
            },
        }).open();
    }

    document.getElementById("click_post").addEventListener("click", function () {
        postcode();
        document.getElementById("address2").focus();
    })
});

