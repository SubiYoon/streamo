// TODO : 이후에 사이드바로 사용

'use client'

const SideBar = ({ isOpen, toggleSidebar }) => {
  return (
    <aside
      className={`
        bg-gray-900 text-white h-screen p-4
        transition-all duration-300 ease-in-out
        ${isOpen ? "w-[240px]" : "w-[60px]"}
      `}
    >
      {/* 햄버거 버튼 */}
      <button
        onClick={toggleSidebar}
        className="bg-gray-700 p-2 rounded text-white"
      >
        {isOpen ? "🔙" : "☰"}
      </button>

      {/* 사이드바 메뉴 */}
      <nav className={`mt-8 ${isOpen ? "block" : "hidden"}`}>
        <ul className="space-y-4">
          <li className="hover:text-yellow-400 cursor-pointer">홈</li>
          <li className="hover:text-yellow-400 cursor-pointer">라이브</li>
          <li className="hover:text-yellow-400 cursor-pointer">내 구독</li>
          <li className="hover:text-yellow-400 cursor-pointer">설정</li>
        </ul>
      </nav>
    </aside>
  );
};

export default SideBar;
