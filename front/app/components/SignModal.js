'use client'

import { useRef, useState } from "react"
import InputBox from "./SignModal/InputBox"
import AuthSwitchMessage from "./SignModal/AuthSwitchMessage"
import SubmitButton from "./SignModal/SubmitButton"

const SignModal = ({onClose}) => {

  const formRef = useRef(null)
  const [isLoginPart, setIsLoginPart] = useState(true)

  // TODO : FORM 외부 클릭 시 모달창 닫기
  const closeModal = (e) => {
    // INFO : e.target이 formRef.current와 같으면 이벤트 전파를 막음
    if (e.target === formRef.current || formRef.current.contains(e.target)) {
      return
    }
    onClose()
  }

  const changePart = () => {
    setIsLoginPart(isLoginPart => !isLoginPart)
  }

  return (
    <div onClick={closeModal} 
    className="fixed w-[100vw] h-[100vh] bg-[transparent] top-0 left-0" >
      <form
        ref={formRef} 
        className="
        bg-[#111111]
        border border-[#191919] rounded-[.4em]
        absolute top-0 bottom-0 right-0 left-0
        shadow-[0_5px_10px_5px_rgba(0,0,0,0.2)]
        m-auto p-[0_20px]
        boxsizing-border-box
        overflow-hidden
        w-sm h-fit

        custom-gradient
        
        after:content-['']
        after:block
        after:w-[150px] after:h-[1px]
        after:absolute after:top-0 after:left-[18%]

        before:content-['']
        before:rounded-full
        before:shadow-[0_0_6px_4px_#fff]
        before:block
        before:w-[8px] before:h-[5px]
        before:absolute before:top-[-7px] before:left-[32%]
      ">
        <h1 className={`
        font-[Audiowide] 
        border-b border-[#000]
        text-lg text-[#ffbb00]
        font-normal
        p-[15px_0]
        relative
        text-center
        text-shadow-[0_1px_0_#000]

        after:content-['']
        after:pointer-events-none
        after:absolute after:top-0
        after:block
        after:w-[300px]
        after:h-[240px]
        after:ml-[44%]
        after:transform-flat
        after:skew-x-20
        after:bg-linear-to-b 
        after:from-[hsla(0,0%,100%,0.1)] 
        after:to-[hsla(0,0%,100%,0)]
        `}>
        Streamo
        </h1>

        <InputBox isLoginPart={isLoginPart} />
        <AuthSwitchMessage isLoginPart={isLoginPart} handleClick={changePart}/>

        <SubmitButton isLoginPart={isLoginPart} handleClick={null}/>
      </form>
    </div>
    
  )
}

export default SignModal
