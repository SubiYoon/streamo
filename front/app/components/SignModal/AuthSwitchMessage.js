const AuthSwitchMessage = ({ isLoginPart=true, handleClick=null }) => {

  return (
    <p className="
    text-[#fff] text-center text-sm">
      {isLoginPart ? "아직 회원이 아니신가요?" : "이미 회원이신가요?"}
      
      <button 
      type="button" onClick={handleClick}
      className="
      text-[#ffbb00]
      ml-2
      cursor-pointer
      ">
      {isLoginPart ? "회원가입하기" : "로그인하기"}</button>
    </p>
  )
}

export default AuthSwitchMessage