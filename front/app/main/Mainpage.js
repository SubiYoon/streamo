'use client'
import { useState } from 'react'
import SignModal from '../components/SignModal'
import SideBar from '../components/SideBar'

const MainPage = () => {
  const [signInModalOpen, setSignInModalOpen] = useState(false)
  const [isSidebarOpen, setIsSidebarOpen] = useState(true)

  const openSignModal = () => {
    console.log('로그인 버튼 클릭됨')
    setSignInModalOpen(true)
  }

  return (
    <div className="flex h-screen">
      {/* 사이드바 */}
      <SideBar isOpen={isSidebarOpen} toggleSidebar={() => setIsSidebarOpen(!isSidebarOpen)} />

      {/* 메인 컨텐츠 */}
      <main
        className={`
          bg-gray-800 text-white p-8 flex-1 transition-all duration-300 ease-in-out
        `}
      >
        <h1 className="text-2xl font-bold">Streamo 메인 페이지</h1>
        <p className="mt-2">Welcome to the main page!</p>
        <button
          onClick={openSignModal}
          className="mt-4 px-6 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded"
        >
          로그인
        </button>
        {signInModalOpen && <SignModal onClose={() => setSignInModalOpen(false)} />}
      </main>
    </div>
  )
}

export default MainPage;
