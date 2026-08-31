import { useState } from 'react'

function Admin(){

    const API_URL = import.meta.env.VITE_API_URL

    const[username, setUsername] = useState('')
    const [name, setName] = useState('')
    const [password, setPassword] = useState('')
    const [successMessage, setSuccessMessage] = useState('')
    const [errorMessage, setErrorMessage] = useState('')
    const [targetUsername, setTargetUsername] = useState('')
    const [newPassword, setNewPassword] = useState('')
    const [employeeID, setEmployeeID] = useState('')

    async function createEmployee(event){
        event.preventDefault()

        setSuccessMessage('')
        setErrorMessage('')

        const createRequest = {username, name, password}

        try{
            const response = await fetch(`${API_URL}/api/authorization/create`, {method: 'POST', headers: {'Content-Type': 'application/json'}, credentials: 'include', body: JSON.stringify(createRequest)})
            if (response.ok){
                setSuccessMessage('Employee created successfully.')
                setUsername('')
                setName('')
                setPassword('')
            }
            else{
                setErrorMessage('Unable to create employee.')
            }
        }
        catch(error){
            console.log(error)
            setErrorMessage('Unable to connect to the server.')
        }
    }
    async function resetEmployeePassword(event){
        event.preventDefault()
        setSuccessMessage('')
        setErrorMessage('')
        const resetRequest = {username: targetUsername, newPassword}
        try{
            const response = await fetch(`${API_URL}/api/authorization/admin/password`, {method: 'PUT', headers: {'Content-Type': 'application/json'}, credentials: 'include', body: JSON.stringify(resetRequest)})
            if (response.ok){
                const passwordChanged = await response.json()
                if (passwordChanged){
                    setSuccessMessage('Employee password reset successfully.')
                    setTargetUsername('')
                    setNewPassword('')
                }
                else{
                    setErrorMessage('Employee not found.')
                }
            }
            else{
                setErrorMessage('Unable to reset employee password.')
            }
        }
        catch(error){
            console.log(error)
            setErrorMessage('Unable to connect to the server.')
        }
    }
    return(
        <div>
            <h1>Admin</h1>
            <h2>Create Employee</h2>
            <form onSubmit={createEmployee}>
                <div>
                    <label>Name</label>
                    <input type="text" value={name} onChange={(event) => setName(event.target.value)} required/>
                </div>
                <div>
                    <label>Username</label>
                    <input type="text" value={username} onChange={(event) => setUsername(event.target.value)} required/>
                </div>
                <div>
                    <label>Password</label>
                    <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} required/>
                </div>
                <button type="submit">Create Employee</button>
            </form>

            {successMessage && <p>{successMessage}</p>}
            {errorMessage && <p>{errorMessage}</p>}
        </div>
    )
}

export default Admin