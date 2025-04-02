import ModalInput from './ModalInput'

const InputBox = ({ isLoginPart=true }) => {
  return (
    <div className="border-t border-[#19191a] p-[20px_0]">
      
      <ModalInput type="text" placeholder="아이디" autoFocus={true}/>
      <ModalInput type="password" placeholder="비밀번호" />
      {!isLoginPart && (
      <>
        <ModalInput type="password" placeholder="비밀번호 확인" />
        <ModalInput type="text" placeholder="이름" />
        <ModalInput type="text" placeholder="생년월일" />  
      </>
      )}
    </div>
  ) 
}

export default InputBox