import { useState } from 'react'
import './App.css'
import Login from './Login'
import Requests from './Requests'
import Admin from './Admin'

function App() {

  const [loggedIn, setLoggedIn] = useState(false)
  const [currentUser, setCurrentUser] = useState(null)

  return (
    <div>
      {loggedIn
      ? <div>
        <Requests setLoggedIn={setLoggedIn} currentUser={currentUser}/>
        {currentUser . role === 'ADMIN' && <Admin />}
      </div>
      : <Login setLoggedIn={setLoggedIn} setCurrentUser={setCurrentUser}/>
      }
    </div>
  )
}

export default App
