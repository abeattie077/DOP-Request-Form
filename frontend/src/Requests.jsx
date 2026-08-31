import { useState, useEffect } from 'react'
import ChangePassword from './ChangePassword'

function Requests({setLoggedIn, currentUser}){

    const API_URL = import.meta.env.VITE_API_URL

    const[requests, setRequests] = useState([])
    const[errorMessage, setErrorMessage] = useState('')

    async function getRequests(){
        try{
            const response = await fetch(`${API_URL}/api/requests`, {method: 'GET', credentials: 'include'})

            if (response.ok){
                const requestData = await response.json()
                setRequests(requestData)
            }
            else{
                setErrorMessage('Unable to retrive requests.')
            }
        }
        catch(error){
            console.log(error)
            setErrorMessage('Unable to retrieve requests')
        }
    }    
        useEffect(() => {
            getRequests()}, [API_URL])

    const sortedRequests = [...requests].sort(
        (a,b) => new Date(b.submittedAt) - new Date(a.submittedAt)
    )

    async function markContacted(requestID){
        try{
            const response = await fetch(`${API_URL}/api/requests/${requestID}/contacted`, {method: 'PUT', credentials: 'include'})

            if (response.ok){
                await getRequests()
            }
            else{
                setErrorMessage('Unable to mark request as contacted')
            }
        }
        catch(error){
            console.log(error)
            setErrorMessage('Unable to connect to the server.')
        }
    }

    async function logout(){
        try{
            const response = await fetch(`${API_URL}/api/authorization/logout`, {method: 'POST', credentials: 'include'})
            if (response.ok){setLoggedIn(false)}
            else{setErrorMessage('Unable to log out.')}
        }
        catch(error){
            console.log(error)
            setErrorMessage('Unable to connect to the server.')
        }
    }

return(
    <div>
        <p>Logged in as: {currentUser.name}</p>
        <p>Role: {currentUser.role}</p>
        <h1>Service Requests</h1>

        {errorMessage && <p>{errorMessage}</p>}

        <p>Requests found: {sortedRequests.length}</p>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Company</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Service Date</th>
                    <th>Units</th>
                    <th>DOP Testing</th>
                    <th>Filter Change</th>
                    <th>Other</th>
                    <th>Unit Details</th>
                    <th>Additional Information</th>
                    <th>Submitted</th>
                    <th>Contacted</th>
                    <th>Contacted By</th>
                </tr>
            </thead>
            <tbody>
                {sortedRequests.map((request) => (
                    <tr key={request.requestID}>
                        <td>{request.requestID}</td>
                        <td>{request.companyName}</td>
                        <td>{request.requesterFirstName}</td>
                        <td>{request.requesterLastName}</td>
                        <td>{request.customerPhone}</td>
                        <td>{request.customerEmail}</td>
                        <td>{request.serviceAddress}</td>
                        <td>{request.desiredServiceDate}</td>
                        <td>{request.numberOfUnits}</td>
                        <td>{request.dopTesting ? 'Yes' : 'No'}</td>
                        <td>{request.filterChange ? 'Yes' : 'No'}</td>
                        <td>{request.otherServiceRequest ? 'Yes' : 'No'}</td>
                        <td>{request.unitDetails}</td>
                        <td>{request.additionalInformation}</td>
                        <td>{request.submittedAt}</td>
                        <td>{request.customerHasBeenContacted 
                        ? 'Yes' 
                        : <button onClick={() => markContacted(request.requestID)}>Mark Contacted</button>}
                        </td>
                        <td>{request.contactedBy}</td>
                    </tr>
                ))}
            </tbody>
        </table>
         <button onClick={logout}>Logout</button>
         <ChangePassword />
    </div>    
)
}

export default Requests