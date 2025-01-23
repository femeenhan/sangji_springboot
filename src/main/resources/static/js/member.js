window.addEventListener('DOMContentLoaded', function (message) {
    const loginBtnEl = document.querySelector('.header_btn_wrap .login');

    // document.querySelector('input[name=id]')?.focus();
    document.querySelector('input[name=username]')?.focus();
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
            // const idEl = document.querySelector('.login_info input[name=id]');
            // const pwEl = document.querySelector('.login_info input[name=pass]');
            const idEl = document.querySelector('.login_info input[name=username]');
            const pwEl = document.querySelector('.login_info input[name=password]');
            const id = idEl.value.trim();
            const pw = pwEl.value.trim();

            if (id.length == 0) {
                alert('아이디를 입력하세요');
                idEl.focus();
                return false;
            } else if (pw.length == 0) {
                alert('비밀번호를 입력하세요');
                pwEl.focus();
                return false;
            }
            return true;
        })
    }

    const idFeild = document.getElementById('id');
    const saveIdCheckBox = document.getElementById('saveId');
    if (saveIdCheckBox) {
        const savedId = localStorage.getItem('savedId');
        if (savedId) {
            idFeild.value = savedId;
            saveIdCheckBox.checked = true;
        }

        saveIdCheckBox.addEventListener('change', function () {
            if (this.checked) {
                localStorage.setItem('savedId', idFeild.value);
            } else {
                localStorage.removeItem('savedId');
            }
        })
    }

    // 로그아웃 확인 (html 안에 script)

    // 아이디, 비밀번호 엔터키 이벤트 (로그인)
    // const idEnter = document.querySelector('.login_info input[name=id]');
    // const passEnter = document.querySelector('.login_info input[name=pass]');
    const idEl = document.querySelector('.login_info input[name=username]');
    const pwEl = document.querySelector('.login_info input[name=password]');

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
                    // console.log(idCheckNum);
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
    const emailInput = document.getElementById('input_email');
    const emailAddressInput = document.getElementById('email_address');

    if (domainEl && emailInput && emailAddressInput) {
        domainEl.addEventListener('change', function () {
            const selectedDomain = this.value;
            if (selectedDomain === '') {
                emailAddressInput.value = '';
                emailAddressInput.readOnly = false;
            } else if (selectedDomain === '직접입력') {
                emailAddressInput.value = '';
                emailAddressInput.readOnly = false;
                emailAddressInput.focus();
            } else {
                emailAddressInput.value = '@' + selectedDomain;
                emailAddressInput.readOnly = true;
            }
        });

        // 이메일 입력값 합치기
        emailInput.addEventListener('input', function () {
            const fullEmail = emailInput.value + emailAddressInput.value;
            const hiddenEmailInput = document.createElement('input');
            hiddenEmailInput.type = 'hidden';
            hiddenEmailInput.name = 'email';
            hiddenEmailInput.value = fullEmail;
            emailInput.parentNode.appendChild(hiddenEmailInput);
        });

        emailAddressInput.addEventListener('input', function () {
            const fullEmail = emailInput.value + emailAddressInput.value;
            const hiddenEmailInput = document.querySelector('input[type="hidden"][name="email"]');
            if (hiddenEmailInput) {
                hiddenEmailInput.value = fullEmail;
            }
        });
    }

    // 생년월일 Select
    if (document.getElementById('datepicker')) {
        flatpickr("#datepicker", {
            locale: "ko",
            enableTime: false,
            dateFormat: "Y-m-d",
            maxDate: "today",
        });
    }

    // postcode API
    window.postcode = function () {
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
                document.getElementById('address2').focus();
            }
        }).open();
    }

    const postButton = document.getElementById("click_post");
    if (postButton) {
        postButton.addEventListener("click", function (e) {
            e.preventDefault();
            postcode();
        });
    }

    const saveBtn = document.querySelector(".btn_wrap .save");
    if (saveBtn) {
        saveBtn.addEventListener("keyup", function (e) {
            if (e.key === "Enter")
                document.querySelector(".btn_wrap .save").click();
        })
    }

    // join 항목 검토 (이메일 형식은 추가 확인 필요)
    joinEl.addEventListener('submit', function (e) {
        e.preventDefault();
        // 아이디 중복체크 확인
        if (idCheckNum == 0 && location.pathname.startsWith('/member/join')) {
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
        // const emailEl = document.getElementById('input_email').value.trim();
        //     ^ (== 시작/^), [^\s@]+ (== 공백 문자와 @를 제외한 한 글자 이상의 연속된 문자)
        //     @ (== @반드시 포함), [^\s@]+ (== @를 제외한 한 글자 이상의 연속된 문자)
        //     \. (== . 이후), [^\s@]+ (== 역시 공백 문자와 @를 제외한 문자), $ (== 이렇게 끝나야 함)
        // const emailRegex = /^[^\s@]/;
        // if (!emailRegex.test(emailEl)) {
        //     alert('올바른 이메일 형식을 입력해주세요');
        //     return;
        // }
        // 휴대폰 번호 형식 확인
        const phone = document.getElementById('input_phone').value.trim();
        const phoneRegex = /^(010[-.\s]?\d{4}[-.\s]?\d{4}|01[16789][-.\s]?\d{3,4}[-.\s]?\d{4})$/;
        if (!phoneRegex.test(phone)) {
            alert("휴대폰 번호 형식이 올바르지 않습니다.");
            return;
        }
        // form 데이터 수집
        const formData = new FormData(this);
        // memberDTO에 들어갈 수 있는 json 타입만 추출
        // const jsonData = Object.fromEntries(formData.entries());
        const jsonData = {};
        formData.forEach((value, key) => {
            jsonData[key] = value;
        });
        // 서버로 데이터 전송
        fetch('/rest/join_insert', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(jsonData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('서버 응답에 문제가 있습니다.');
                }
                return response.json();
            })
            .then(result => {
                if (result.status === "OK") {
                    alert(result.message);
                    location.href = '/member/join_result';
                } else {
                    alert('잠시 후 다시 시도하시거나 \n관리자에게 문의하세요.');
                }
            })
            .catch(error => {
                console.error('에러 발생:', error);
                alert('회원가입 처리 중 문제가 발생했습니다.');
            });
    });

    // 회원정보 수정


});

