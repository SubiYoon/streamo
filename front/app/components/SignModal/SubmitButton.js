const SubmitButton = ({ isLoginPart=true, handleClick=null }) => {

  return (
    <button
    type="submit" onClick={handleClick} 
    className="
    bg-[#ffbb00]
    border border-[rgba(0,0,0,0.4)]
    rounded-[.3em]
    
    text-[#873c00]
    text-sm
    font-bold
    cursor-pointer
    m-[15px_0] p-[5px_20px]
    w-full h-[40px]

    active:shadow-[inset_0_6px_15px_rgba(0,0,0,0.3)]
    ">
      {isLoginPart ? "로그인" : "회원가입"}
    </button>
  )
}

export default SubmitButton