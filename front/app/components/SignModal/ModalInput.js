const ModalInput = ({type="text", placeholder="입력값 넣을 정보", ...props}) => {

  return (
    <p className="">
      <input 
        type={type} placeholder={placeholder} {...props}
        
        className="
        bg-linear-to-b from-[#1f2124] to-[#27292c]
        border border-[#222]
        rounded-[.3em]
        shadow-[0_1px_0_rgba(255,255,255,0.1)]
        text-[#fff]
        text-[13px]
        mb-[20px]
        px-2 py-2
        w-full
        
        hover:bg-none
        hover:bg-[#27292c]
        "
        
      />
    </p>
  )
}

export default ModalInput