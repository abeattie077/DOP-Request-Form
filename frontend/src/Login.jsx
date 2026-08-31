import { useState } from 'react'

function Login(){
    const API_URL = import.meta.env.VITE_API_URL
    const[username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [errorMessage, setErrorMessage] = useState('')

    async function handleSubmit(event){
        event.preventDefault()
        setErrorMessage('')

        const loginRequest = {username, password}

        try{
            const response = await fetch(`${API_URL}/api/authorization`, {method: 'POST', headers: {'Content-Type': 'application/json'}, credentials: 'include', body: JSON.stringify(loginRequest)})
            if (response.ok){
                const authenticated = await response.json()
                if (authenticated){
                    console.log('Login Successful')}
                else{
                    setErrorMessage('Invalid username or password')
                }
             }    
            else{setErrorMessage('Invalid username or password')}
        }
        catch(error){
            console.log(error)
            setErrorMessage('Unable to connect to the server')
        }
    }

    return (
        <div>
            <h1>Employee Login</h1>

            <form onSubmit={handleSubmit}>
                <div>
                    <label>Username</label>
                    <input type="text" value={username} onChange={(event) => setUsername(event.target.value)} required />
                </div>  
                 <div>
                    <label>Password</label>
                    <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} required />
                </div>   
                <button type="submit">Login</button>
                {errorMessage && <p>{errorMessage}</p>}
            </form>    
        </div>    
    )
}

export default Login