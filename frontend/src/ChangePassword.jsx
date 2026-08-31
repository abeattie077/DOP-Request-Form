import { useState } from 'react'

function ChangePassword(){

    const API_URL = import.meta.env.VITE_API_URL

    const[currentPassword, setCurrentPassword] = useState('')
    const [newPassword, setNewPassword] = useState('')
    const [successMessage, setSuccessMessage] = useState('')
    const [errorMessage, setErrorMessage] = useState('')

    async function changePassword(event){
        event.preventDefault()
        setSuccessMessage('')
        setErrorMessage('')
        const passwordRequest = {currentPassword, newPassword}

        try{
            const response = await fetch(`${API_URL}/api/authorization/password`, {method: 'PUT', headers: {'Content-Type': 'application/json'}, credentials: 'include', body: JSON.stringify(passwordRequest)})
            if (response.ok){
                const passwordChanged = await response.json()
                if (passwordChanged){
                    setSuccessMessage('Password changed successfully.')
                    setCurrentPassword('')
                    setNewPassword('')
                }
                else{
                    setErrorMessage('Current password is incorrect.')
                }
            }
            else{
                setErrorMessage('Unable to change password.')
            }
        }
        catch(error){
            console.log(error)
            setErrorMessage('Unable to connect to the server.')
        }
    }
    return(
        <div>
            <h2>Change Password</h2>
            <form onSubmit={changePassword}>
                <div>
                    <label>Current Password</label>
                    <input type="password" value={currentPassword} onChange={(event) => setCurrentPassword(event.target.value)} required/>
                </div>
                 <div>
                    <label>New Password</label>
                    <input type="password" value={newPassword} onChange={(event) => setNewPassword(event.target.value)} required/>
                </div>
                <button type="submit">Change Password</button>
            </form>
            {successMessage && <p>{successMessage}</p>}
            {errorMessage && <p>{errorMessage}</p>}
        </div>
    )
}
export default ChangePassword