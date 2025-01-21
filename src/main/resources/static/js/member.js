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
                if (id === ${id} && pw === ${pw}) {
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
    let idCheckNum = 0;
    if (joinEl) {
        const checkBtn = document.getElementById("id_check");
        const idEl = document.getElementById("input_text_id");
        checkBtn.addEventListener("click", function (e) {
            e.preventDefault();
            const id = idEl.value;
            if (id.length == 0) {
                alert('아이디를 입력하세요');
                idEl.focus();
                return;
            }
            fetch(`/rest/idCheck/${id}`, {
                method: 'GET',
            }).then(response => {
                // 요청 성공
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('서버 응답에 문제가 있습니다.');
                }
            }).then(result => {
                if (result.status === "0") {
                    idCheckNum++;
                    console.log(idCheckNum);
                    if (!confirm(result.message)) {
                        idCheckNum = 0;
                    }
                } else {
                    alert('사용 중인 아이디입니다. 다른 아이디를 입력하세요.');
                }
            }).catch(error => {
                console.log('에러 발생:', error);
                alert('아이디 확인 중 문제가 발생했습니다.');
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


    const saveBtn = document.querySelector(".btn_wrap .save");
    if (saveBtn) {
        saveBtn.addEventListener("keyup", function (e) {
            if (e.key === "Enter")
                document.querySelector(".btn_wrap .save").click();
        })
    }

    // join ok
    joinEl.addEventListener('submit', function (e) {
        e.preventDefault();
        // 아이디 중복체크 확인
        if (idCheckNum == 0) {
            alert('아이디 중복체크를 해주세요');
            return;
        }
        // 비밀번호 확인
        const pwEl = document.getElementById('input_pw').value.trim();
        const pwChEl = document.getElementById('input_pw_ch').value.trim();
        if (pwEl != pwChEl) {
            alert('같은 비밀번호를 입력해주세요');
            return;
        }
        // 이메일 형식 확인
        const emailEl = document.getElementById('input_email').value.trim();
        //     ^ (== 시작/^), [^\s@]+ (== 공백 문자와 @를 제외한 한 글자 이상의 연속된 문자)
        //     @ (== @반드시 포함), [^\s@]+ (== @를 제외한 한 글자 이상의 연속된 문자)
        //     \. (== . 이후), [^\s@]+ (== 역시 공백 문자와 @를 제외한 문자), $ (== 이렇게 끝나야 함)
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(emailEl)) {
            alert('올바른 이메일 형식을 입력해주세요');
            return;
        }
        // 휴대폰 번호 형식 확인
        const phone = document.getElementById('input_phone').value.trim();
        const phoneRegex = /^(010[-.\s]?\d{4}[-.\s]?\d{4}|01[16789][-.\s]?\d{3,4}[-.\s]?\d{4})$/;
        if (!phoneRegex.test(phone)) {
            alert("휴대폰 번호 형식이 올바르지 않습니다.");
            return;
        }
        // 입력 데이터 전송
        const formData = new FormData(this);
        // memberDTO에 들어갈 수 있는 json 타입만 추출
        const jsonData = Object.fromEntries(formData.entries());

        // 회원정보 수정


    })

    // 로그인

});

